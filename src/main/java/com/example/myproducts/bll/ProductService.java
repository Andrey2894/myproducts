package com.example.myproducts.bll;

import com.example.myproducts.dal.Product;
import com.example.myproducts.exceptions.IdException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {
    private static List<Product> productList = new ArrayList<>();

    public List<Product> listAll() {
        return productList;
    }

    public Product getProductById(Long id) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return product;

            }
        }
        throw new IdException();
    }

    public Product createProduct(Product product) {
        if (product.getId() == null) return null;
        if (product.getName() == null || product.getName().length() > 256) return null;
        if (product.getDescription() == null || product.getDescription().length() > 4096) product.setDescription("");
        if (product.getPrice() < 0) product.setPrice(0);
        productList.add(product);
        return product;
    }

    public Product updateProduct(Product product,Long id) {
        product.setId(id);
        if (product.getName() == null || product.getName().length() > 256) return null;
        if (product.getDescription() == null || product.getDescription().length() > 4096) product.setDescription("");
        if (product.getPrice() < 0) product.setPrice(0);

        Product product1 = getProductById(id);
        if (product1 != null) {
            int i = productList.indexOf(product1);
            productList.set(i,product);
            return product;
        }
        return null;
    }


    public void deleteProductById(Long id) {
        productList.remove(getProductById(id));
    }

}
