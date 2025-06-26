package com.warehouse_test.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record UpdateInventoryDTO(
        @NotNull(message = "Inventory ID is required")
        Long id,

        @Positive(message = "Quantity must be positive")
        Integer quantity,

        @Min(value = 1, message = "Minimum stock must be at least 1")
        Integer minimumStock,

        @Min(value = 1, message = "Maximum stock must be at least 1")
        Integer maximumStock,

        LocalDateTime lastRestocked
) {
    // vazw kai enan constructor na elegxei oti einai ligotero to min apo to  max.
    public UpdateInventoryDTO {
        if (minimumStock != null && maximumStock != null && maximumStock <= minimumStock) {
            throw new IllegalArgumentException("Maximum stock must be greater than minimum stock");
        }
    }
}