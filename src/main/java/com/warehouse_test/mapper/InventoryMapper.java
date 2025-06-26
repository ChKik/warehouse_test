package com.warehouse_test.mapper;

import com.warehouse_test.dto.CreateInventoryDTO;
import com.warehouse_test.dto.UpdateInventoryDTO;
import com.warehouse_test.dto.InventoryResponseDTO;
import com.warehouse_test.entity.Inventory;
import com.warehouse_test.entity.Product;
import com.warehouse_test.entity.Warehouse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    // Update an existing inventory entity (for PUT/PATCH operations)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "warehouse", ignore = true)
    @Mapping(target = "product", ignore = true)
    void updateInventoryFromDto(UpdateInventoryDTO dto, @MappingTarget Inventory inventory);

    // Convert entity to response DTO
    InventoryResponseDTO toResponseDTO(Inventory inventory);

    // Custom method to build Inventory from CreateInventoryDTO + fetched Warehouse/Product
    //To ekane to chatgpt ayto giati exei ta fields toy productid kai to warehouseid mono to inventory
    default Inventory toEntity(CreateInventoryDTO dto, Warehouse warehouse, Product product) {
        Inventory inventory = new Inventory();
        inventory.setWarehouse(warehouse);  //reference se hdh yparxoyn warehouse kai product.
        inventory.setProduct(product);
        inventory.setQuantity(dto.quantity());
        inventory.setMinimumStock(dto.minimumStock());
        inventory.setMaximumStock(dto.maximumStock());
        inventory.setLastRestocked(dto.lastRestocked());
        return inventory;
    }
}
