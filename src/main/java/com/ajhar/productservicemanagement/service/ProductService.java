package com.ajhar.productservicemanagement.service;

import com.ajhar.productservicemanagement.entity.Product;

import java.util.List;

public interface ProductService {
    List <Product> getAllProducts();
    Product getProductById(Long id);
    Product create(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
}
