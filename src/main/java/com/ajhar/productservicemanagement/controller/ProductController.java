package com.ajhar.productservicemanagement.controller;

import com.ajhar.productservicemanagement.entity.Product;
import com.ajhar.productservicemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    public ProductService getProductService() {
        return productService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //http://localhost:8081/api/products
    //http://localhost:8081/h2-console
    //http://localhost:8081/logout
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product){
        return productService.update(id,product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }
}
