package com.ajhar.productservicemanagement.service;

import com.ajhar.productservicemanagement.dto.ProductPageResponse;
import com.ajhar.productservicemanagement.entity.Product;

import java.util.List;

public interface ProductService {

    /** Start AJHAR20251129 Commenting  getAllProducts after pagination & sorting implementation **/
//    List <Product> getAllProducts();
    /** End AJHAR20251129 Commenting  getAllProducts after pagination & sorting implementation **/

    Product getProductById(Long id);
    Product create(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
    ProductPageResponse getProducts(int page, int size, String sort);

}
