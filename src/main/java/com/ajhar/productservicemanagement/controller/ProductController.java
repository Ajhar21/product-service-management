package com.ajhar.productservicemanagement.controller;

import com.ajhar.productservicemanagement.dto.ProductPageResponse;
import com.ajhar.productservicemanagement.dto.ProductRequest;
import com.ajhar.productservicemanagement.dto.ProductResponse;
import com.ajhar.productservicemanagement.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Endpoints for managing products")
@SecurityRequirement(name = "bearerAuth")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ===================== GET ALL PRODUCTS =====================
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(
            summary = "Get paginated and sorted list of products",
            description = "Returns products with pagination and sorting support."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved products",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductPageResponse.class),
                            examples = @ExampleObject(
                                    name = "Sample response",
                                    value = """
                                            {
                                              "content": [
                                                {
                                                  "id": 1,
                                                  "name": "Super emulated display",
                                                  "description": "Iphone 10",
                                                  "price": 100000,
                                                  "createdAt": "2025-11-29T14:57:24.412035",
                                                  "updatedAt": "2025-11-29T14:57:24.412035"
                                                }
                                              ],
                                              "totalElements": 5,
                                              "totalPages": 1,
                                              "pageNumber": 0,
                                              "pageSize": 10,
                                              "last": true
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<ProductPageResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        ProductPageResponse response = productService.getProducts(page, size, sort);
        return ResponseEntity.ok(response);
    }

    // ===================== GET PRODUCT BY ID =====================
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(
            summary = "Get product by ID",
            description = "Fetch a single product by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // ===================== CREATE PRODUCT =====================
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    @Operation(
            summary = "Create a product",
            description = "Add a new product to the system"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Product created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class),
                            examples = @ExampleObject(
                                    name = "Created product example",
                                    value = """
                                            {
                                              "message": "Product created successfully",
                                              "product": {
                                                "id": 1,
                                                "name": "New Product",
                                                "description": "Description here",
                                                "price": 12345,
                                                "createdAt": "2025-11-29T14:57:24.412035",
                                                "updatedAt": "2025-11-29T14:57:24.412035"
                                              }
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody ProductRequest request) {
        ProductResponse createdProduct = productService.create(request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product created successfully");
        response.put("product", createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ===================== UPDATE PRODUCT =====================
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Update a product",
            description = "Update an existing product by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request
    ) {
        ProductResponse updatedProduct = productService.update(id, request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product updated successfully");
        response.put("product", updatedProduct);
        return ResponseEntity.ok(response);
    }

    // ===================== DELETE PRODUCT =====================
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Delete a product",
            description = "Delete a product by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        productService.delete(id);
        Map<String, String> response = Map.of(
                "message", "Product deleted successfully",
                "productId", id.toString()
        );
        return ResponseEntity.ok(response);
    }
}
