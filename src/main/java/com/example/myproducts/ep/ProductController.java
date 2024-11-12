package com.example.myproducts.ep;

import com.example.myproducts.bll.ProductService;
import com.example.myproducts.dal.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/listAll")
    public List<Product> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return service.getProductById(id);
    }

    @PostMapping()
    public Product addProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable("id") Long id,@RequestBody Product product) {
        return service.updateProduct(product,id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) {
        service.deleteProductById(id);
    }




}
