package com.example.myproducts.ep;

import com.example.myproducts.dal.Product;
import com.example.myproducts.bll.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService repository;

    @GetMapping("/listAll")
    public List<Product> listAll() {
        return repository.listAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return repository.getProductById(id);
    }

    @PostMapping()
    public Product addProduct(@RequestBody Product product) {
        return repository.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable("id") Long id,@RequestBody Product product) {
        return repository.updateProduct(product,id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) {
        repository.deleteProductById(id);
    }




}
