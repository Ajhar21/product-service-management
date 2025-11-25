package com.ajhar.productservicemanagement.repository;

import com.ajhar.productservicemanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
