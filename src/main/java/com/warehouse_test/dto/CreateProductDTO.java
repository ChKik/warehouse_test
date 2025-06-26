package com.warehouse_test.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductDTO(
    @NotBlank(message = "Name is required")
    @Size(max=100,message="Name cannot exceed 100 characters.")
    String name,

    @NotBlank(message = "Price is required")
    @Positive(message="Price must be positive")
    BigDecimal price,

    @NotBlank(message = "Category is required")
    @Size(max=100,message="Category cannot exceed 100 characters.")
    String category,

    @NotBlank(message = "Weight is required")
    @Positive(message="Weight must be positive")
    BigDecimal weight
) {
}
