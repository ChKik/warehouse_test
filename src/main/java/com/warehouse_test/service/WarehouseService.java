package com.warehouse_test.service;

import com.warehouse_test.entity.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {
    //CRUD
    List<Warehouse> findAllWarehouses();
    Optional<Warehouse> findWarehouseById(long id);
    Warehouse createWarehouse(Warehouse warehouse);
    Warehouse updateWarehouse(long id, Warehouse warehouse);
    void deleteWarehouse(long id);

    //Business Logic
    List<Warehouse> findWarehousesByName(String name);
    List<Warehouse> findWarehouseByCapacity(Integer minimumCapacity, Integer maximumCapacity);
}
