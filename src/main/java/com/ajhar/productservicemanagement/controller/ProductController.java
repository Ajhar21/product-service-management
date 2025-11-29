package com.ajhar.productservicemanagement.controller;

import com.ajhar.productservicemanagement.entity.Product;
import com.ajhar.productservicemanagement.exception.ForbiddenAccessException;
import com.ajhar.productservicemanagement.service.ProductService;
import com.ajhar.productservicemanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Product product) {
        Product createdProduct = productService.create(product);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product created successfully");
        response.put("product", createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.update(id, product);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product updated successfully");
        response.put("product", updatedProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        productService.delete(id);
        Map<String, String> response = Map.of("message", "Product deleted successfully", "productId", id.toString());
        return ResponseEntity.ok(response);
    }
}

