
package com.ajhar.productservicemanagement.service.impl;

import com.ajhar.productservicemanagement.dto.ProductPageResponse;
import com.ajhar.productservicemanagement.dto.ProductRequest;
import com.ajhar.productservicemanagement.dto.ProductResponse;
import com.ajhar.productservicemanagement.entity.Product;
import com.ajhar.productservicemanagement.exception.ProductNotFoundException;
import com.ajhar.productservicemanagement.repository.ProductRepository;
import com.ajhar.productservicemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

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

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        return mapToProductResponse(product);
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        Product product = new Product(
                request.getName(),
                request.getDescription(),
                request.getPrice()
        );

        Product saved = productRepository.save(product);
        return mapToProductResponse(saved);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());

        Product updated = productRepository.save(existing);
        return mapToProductResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
