package com.ajhar.productservicemanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Super AMOLED Display")
    private String name;

    @Schema(example = "High quality phone display")
    private String description;

    @Schema(example = "99999.99")
    private BigDecimal price;

    @Schema(example = "1")
    private Long version;

    @Schema(example = "2025-11-29T14:57:24.412035")
    private LocalDateTime createdAt;

    @Schema(example = "2025-11-29T15:10:00.123456")
    private LocalDateTime updatedAt;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String name, String description,
                           BigDecimal price, Long version, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getVersion() {
        return version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
