package com.ajhar.productservicemanagement.service.impl;

import com.ajhar.productservicemanagement.dto.ProductPageResponse;
import com.ajhar.productservicemanagement.dto.ProductResponse;
import com.ajhar.productservicemanagement.entity.Product;
import com.ajhar.productservicemanagement.exception.ProductNotFoundException;
import com.ajhar.productservicemanagement.repository.ProductRepository;
import com.ajhar.productservicemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductPageResponse getProducts(int page, int size, String sort) {
        // parse sort string like "id,asc"
        String sortField = "id";
        Sort.Direction direction = Sort.Direction.ASC;

        if (sort != null && !sort.isBlank()) {
            String[] parts = sort.split(",");
            if (parts.length > 0 && !parts[0].isBlank()) {
                sortField = parts[0];
            }
            if (parts.length > 1 && !parts[1].isBlank()) {
                direction = Sort.Direction.fromString(parts[1]);
            }
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductResponse> content = productPage.getContent()
                .stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());

        return new ProductPageResponse(
                content,
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.getNumber(),
                productPage.getSize(),
                productPage.isLast()
        );
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),   // 3rd: description
                product.getPrice(),         // 4th: price
                product.getCreatedAt(),     // 5th: createdAt
                product.getUpdatedAt()      // 6th: updatedAt
        );
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: "+id));
    }

    public Product create(Product product){
        return productRepository.save(product);
    }

    public Product update(Long id, Product product){
        Product existing= this.getProductById(id);
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        return productRepository.save(existing);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }
}
