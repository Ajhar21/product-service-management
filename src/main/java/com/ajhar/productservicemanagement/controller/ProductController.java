package com.ajhar.productservicemanagement.controller;

import com.ajhar.productservicemanagement.entity.Product;
import com.ajhar.productservicemanagement.service.ProductService;
import com.ajhar.productservicemanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

// Custom exception for 403 Forbidden
@ResponseStatus(HttpStatus.FORBIDDEN)
class ForbiddenAccessException extends RuntimeException {
    public ForbiddenAccessException(String message) {
        super(message);
        System.out.println(message);
    }
}

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;
    private AdminService adminService;

    @Autowired
    public ProductController(ProductService productService, AdminService adminService) {
        this.productService = productService;
        this.adminService = adminService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product, @RequestHeader("User-Email") String currentUserEmail) {
        System.out.println("Received Product: " + product);
        System.out.println("Current User: " + currentUserEmail);
        // Check if current user is admin
        if (!adminService.isAdmin(currentUserEmail)) {
            throw new ForbiddenAccessException("You do not have permission to create products.");
        }
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product, @RequestHeader("User-Email") String currentUserEmail) {
        // Check if current user is admin
        if (!adminService.isAdmin(currentUserEmail)) {
            throw new ForbiddenAccessException("You do not have permission to update products.");
        }
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @RequestHeader("User-Email") String currentUserEmail) {
        // Check if current user is admin
        if (!adminService.isAdmin(currentUserEmail)) {
            throw new ForbiddenAccessException("You do not have permission to delete products.");
        }
        productService.delete(id);
    }
}
