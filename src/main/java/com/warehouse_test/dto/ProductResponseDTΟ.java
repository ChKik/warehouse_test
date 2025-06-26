package com.warehouse_test.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDTΟ(
        Long id,
        String name,
        BigDecimal price,
        String category,
        BigDecimal weight,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime deletedAt

) {
}
