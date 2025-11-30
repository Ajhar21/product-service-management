package com.ajhar.productservicemanagement.service;

import com.ajhar.productservicemanagement.dto.ProductPageResponse;
import com.ajhar.productservicemanagement.dto.ProductRequest;
import com.ajhar.productservicemanagement.dto.ProductResponse;

//====================== Start AJHAR20251130 Cleaning code for DTOs ==============================

public interface ProductService {

    ProductPageResponse getProducts(int page, int size, String sort);

    ProductResponse getProductById(Long id);

    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);
}
//====================== END AJHAR20251130 Cleaning code for DTOs ==============================