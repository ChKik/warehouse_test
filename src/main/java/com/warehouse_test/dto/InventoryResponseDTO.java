package com.warehouse_test.dto;
import com.warehouse_test.entity.Warehouse;
import com.warehouse_test.entity.Product;

import java.time.LocalDateTime;


public record InventoryResponseDTO(
        Warehouse warehouse,
        Product product,
        Integer quantity,
        Integer minimumStock,
        Integer maximumStock,
        LocalDateTime lastRestocked
) {
}
