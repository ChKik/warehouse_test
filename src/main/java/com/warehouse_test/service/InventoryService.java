package com.warehouse_test.service;

import com.warehouse_test.entity.Inventory;
import com.warehouse_test.dto.CreateInventoryDTO;
import com.warehouse_test.dto.UpdateInventoryDTO;
import com.warehouse_test.dto.InventoryResponseDTO;


import java.util.List;
import java.util.Optional;

public interface InventoryService {
    //CRUD
    List<InventoryResponseDTO> getInventory();
    Optional<InventoryResponseDTO> getInventoryById(long id);
    CreateInventoryDTO createInventory(CreateInventoryDTO inventory);
    UpdateInventoryDTO updateInventory(long id,UpdateInventoryDTO inventory);
    void deleteInventory(long id);

    //business logic
    List<InventoryResponseDTO> getInventoryByProductQuantity(long productId,long quantity);
    List<InventoryResponseDTO> getProductByWarehouseId(long warehouseId);
}
