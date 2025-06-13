package com.warehouse_test.service;

import com.warehouse_test.entity.Inventory;


import java.util.List;
import java.util.Optional;

public interface InventoryService {
    //CRUD
    List<Inventory> getInventory();
    Optional<Inventory> getInventoryById(long id);
    Inventory createInventory(Inventory inventory);
    Inventory updateInventory(long id,Inventory inventory);
    void deleteInventory(long id);

    //business logic
    List<Inventory> getInventoryByProductQuantity(long productId,long quantity);
    List<Inventory> getProductByWarehouseId(long warehouseId);
}
