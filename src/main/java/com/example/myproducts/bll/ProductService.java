package com.example.myproducts.bll;

import com.example.myproducts.dal.Product;
import com.example.myproducts.dal.ProductFilterRequest;
import com.example.myproducts.dal.ProductRepository;
import com.example.myproducts.exceptions.IdNotFoundException;
import com.example.myproducts.exceptions.NameException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) throw new IdNotFoundException();
        return product;
    }

    public Product createProduct(Product product) {
        validateProduct(product);
        productRepository.save(product);
        return product;
    }

    public Product updateProduct(Product product, Long id) {
        validateProduct(product);
        product.setId(id);
        productRepository.save(product);
        return product;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().length() > 256) throw new NameException();
        if (product.getDescription() == null || product.getDescription().length() > 4096) product.setDescription("");
        if (product.getPrice() < 0) product.setPrice(0L);
    }

    public List<Product> getFilteredAndSortedProducts(ProductFilterRequest productFilterRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        // Фильтрация по названию (частичное совпадение)
        if (productFilterRequest.getName() != null && !productFilterRequest.getName().isEmpty()) {
            predicates.add(cb.like(cb.lower(product.get("name")), "%" + productFilterRequest.getName().toLowerCase() + "%"));
        }

        // Фильтрация по цене (минимальная и максимальная)
        if (productFilterRequest.getMinPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("price"), productFilterRequest.getMinPrice()));
        }
        if (productFilterRequest.getMaxPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(product.get("price"), productFilterRequest.getMaxPrice()));
        }

        // Фильтрация по наличию товара
        if (productFilterRequest.getAvailable() != null) {
            predicates.add(cb.equal(product.get("isAvailable"), productFilterRequest.getAvailable()));
        }

        // Объединение всех условий фильтрации
        cq.where(predicates.toArray(new Predicate[0]));

        // Настройка сортировки
        if ("desc".equalsIgnoreCase(productFilterRequest.getSortOrder())) {
            cq.orderBy(cb.desc(product.get(productFilterRequest.getSortBy())));
        } else {
            cq.orderBy(cb.asc(product.get(productFilterRequest.getSortBy())));
        }

        // Пагинация
        var query = entityManager.createQuery(cq);
        if (productFilterRequest.getLimit() != null && productFilterRequest.getLimit() > 0) {
            query.setMaxResults(productFilterRequest.getLimit());
        }
        return query.getResultList();
    }

}
