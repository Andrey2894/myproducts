package com.example.myproducts.bll;

import com.example.myproducts.dal.Product;
import com.example.myproducts.dal.ProductRepository;
import com.example.myproducts.exceptions.IdNotFoundException;
import com.example.myproducts.exceptions.NameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

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
}
