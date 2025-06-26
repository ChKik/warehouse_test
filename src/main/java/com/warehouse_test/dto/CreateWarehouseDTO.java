package com.warehouse_test.dto;

//den xreiazetai gia ta id kai ta created timestamps.

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateWarehouseDTO(
    @NotBlank(message="Name is required") //to name einai must den ginetai na einai null
    @Size(max=100,message="Name cannot exceed 50 characters.")
    String warehouseName,

    @NotBlank(message="Address required")
    @Size(max=100,message = "Address cannot exceed 100 characters")
    String address,

    @NotBlank(message="Warehouse Capacity is required")
    @Min(value=1)
    @Positive(message = "Capacity must be positive")
    Integer capacity,

    @NotBlank(message="Manager name is required")
    @Size(max=30, message = "Name is too long.")
    String managerName

) {

}
