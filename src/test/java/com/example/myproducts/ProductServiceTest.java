package com.example.myproducts;

import com.example.myproducts.bll.ProductService;
import com.example.myproducts.dal.Product;
import com.example.myproducts.exceptions.IdAlreadyExistsException;
import com.example.myproducts.exceptions.IdNotFoundException;
import com.example.myproducts.exceptions.NameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    private ProductService service;

    @BeforeEach
    public void setUp() {
        service = new ProductService();
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product("A","",1000,false);
        service.createProduct(1L, product);
        Product result = service.getProductById(1L);
        assertEquals(result.getName(),"A");
    }

    @Test
    public void testCreateProductWithDuplicateId() {
        Product product1 = new Product("Product A", "Description A", 100, false);
        Product product2 = new Product("Product B", "Description B", 200, false);
        service.createProduct(1L, product1);
        assertThrows(IdAlreadyExistsException.class, () -> service.createProduct(1L, product2));
    }

    @Test
    public void testGetProductById() {
        Product product = new Product("Product A", "Description A", 100, false);
        service.createProduct(1L, product);
        Product retrievedProduct = service.getProductById(1L);
        assertNotNull(retrievedProduct);
        assertEquals("Product A", retrievedProduct.getName());
    }

    @Test
    public void testGetProductByIdNotFound() {
        assertThrows(IdNotFoundException.class, () -> service.getProductById(2L));
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product("Product A", "Description A", 100, false);
        service.createProduct(1L, product);
        Product updatedProduct = new Product("Product A Updated", "Description A Updated", 150,true);
        Product result = service.updateProduct(updatedProduct, 1L);
        assertNotNull(result);
        assertEquals("Product A Updated", result.getName());
        assertEquals(150, result.getPrice());
        assertEquals(true, result.isAvailable());
    }

    @Test
    public void testUpdateProductNotFound() {
        Product product = new Product("Product A", "Description A", 100, false);
        Product result = service.updateProduct(product, 1L);
        assertNull(result);
    }

    @Test
    public void testDeleteProductById() {
        Product product = new Product("Product A", "Description A", 100,false);
        service.createProduct(1L, product);
        service.deleteProductById(1L);
        assertThrows(IdNotFoundException.class, () -> service.getProductById(1L));
    }

    @Test
    public void testValidateProductWithInvalidName() {
        Product product = new Product(null, "Description A", 100,false);
        assertThrows(NameException.class, () -> service.createProduct(1L, product));
    }

    @Test
    public void testValidateProductWithLongDescription() {
        String longDescription = "A".repeat(5000);
        Product product = new Product("Product A", longDescription, 100, false);
        service.createProduct(1L, product);
        assertEquals("", service.getProductById(1L).getDescription());
    }

    @Test
    public void testValidateProductWithNegativePrice() {
        Product product = new Product("Product A", "Description A", -50,false);
        service.createProduct(1L, product);
        assertEquals(0, service.getProductById(1L).getPrice());
    }
}


