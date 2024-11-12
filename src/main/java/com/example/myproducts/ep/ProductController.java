package com.example.myproducts.ep;

import com.example.myproducts.bll.ProductService;
import com.example.myproducts.dal.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping("/products")
@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/listAll")
    public HashMap<Long,Product> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return service.getProductById(id);
    }

    @PostMapping()
    public Product addProduct(@RequestBody Product product,@RequestParam Long id) {
        return service.createProduct(id,product);
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
