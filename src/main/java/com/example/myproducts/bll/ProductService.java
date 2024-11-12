package com.example.myproducts.bll;

import com.example.myproducts.dal.Product;
import com.example.myproducts.exceptions.IdAlreadyExistsException;
import com.example.myproducts.exceptions.IdNotFoundException;
import com.example.myproducts.exceptions.NameException;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class ProductService {
    private HashMap<Long,Product> productMap = new HashMap();

    public HashMap<Long,Product> listAll() {
        return productMap;
    }

    public Product getProductById(Long id) {
        Product product = productMap.get(id);
        if (product == null) throw new IdNotFoundException();
        return product;
    }

    public Product createProduct(Long id,Product product) {
        if (productMap.containsKey(id)) throw new IdAlreadyExistsException();
        if (id == null) return null;
        validateProduct(product);
        productMap.put(id,product);
        return product;
    }

    public Product updateProduct(Product product,Long id) {
        validateProduct(product);
        if (!productMap.containsKey(id)) return null;
        productMap.remove(id);
        productMap.put(id,product);
        return product;
    }


    public void deleteProductById(Long id) {
        productMap.remove(id);
    }

    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().length() > 256) throw new NameException();
        if (product.getDescription() == null || product.getDescription().length() > 4096) product.setDescription("");
        if (product.getPrice() < 0) product.setPrice(0);
    }
}
