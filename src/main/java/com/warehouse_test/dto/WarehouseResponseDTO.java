package com.warehouse_test.dto;

import java.time.LocalDateTime;

public record WarehouseResponseDTO(
    Long id,
    String warehouseName,
    String address,
    String managerName,
    Integer capacity,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {


}
