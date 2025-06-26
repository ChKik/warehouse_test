package com.warehouse_test.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record CreateInventoryDTO(
    @NotNull(message = "Warehouse ID is required")
    Long warehouseId,

    @NotNull(message = "Product ID is required")
    Long productId,

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity must be positive or zero")
    Integer quantity,

    @Min(value = 1,message = "Minimum stock is 1")
    @Positive
    Integer minimumStock,

    @Max(value = 2000,message = "Maximum stock is 2000")
    @Positive
    Integer maximumStock,

    @NotNull(message = "Last Restocked must have a date.")
    @PastOrPresent(message = "Last restocked date must be in past or present")
    LocalDateTime lastRestocked

) {
}
