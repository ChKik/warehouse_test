package com.warehouse_test.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateProductDTO(
    @NotNull(message = "Id is required")
    Long id,

    @Size(max=100, message = "Product name cannot exceed 100 characters.")
    String name,

    @PositiveOrZero(message = "Price must be positive or zero")
    BigDecimal price,

    @Size(max=100, message = "Product name cannot exceed 100 characters.")
    String category,

    @Positive(message = "Weight must be positive")
    BigDecimal weight
) {
}
