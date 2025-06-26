package com.warehouse_test.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdateWarehouseDTO(
    @NotNull(message="Warehouse ID is required")
    Long id,  //mono to id einai must ta ypoloipa einai optional.

    @Size(max=100,message="warehouseName cannot exceed 100 characters.")
    String warehouseName,

    @Size(max=100,message="address cannot exceed 100 characters.")
    String address,

    @Size(max=30,message="managerName cannot exceed 30 characters.")
    String managerName,

    @Min(value = 1)
    @Positive(message = "Capacity must be positive.")
    Integer capacity
) {
}
