package com.warehouse_test.service;

import com.warehouse_test.entity.Warehouse;
import com.warehouse_test.dto.WarehouseResponseDTO;
import com.warehouse_test.dto.CreateWarehouseDTO;
import com.warehouse_test.dto.UpdateWarehouseDTO;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {
    //CRUD
    List<WarehouseResponseDTO> findAllWarehouses();
    Optional<WarehouseResponseDTO> findWarehouseById(long id);
    CreateWarehouseDTO createWarehouse(CreateWarehouseDTO warehouse);
    UpdateWarehouseDTO updateWarehouse(long id, UpdateWarehouseDTO warehouse);
    void deleteWarehouse(long id);

    //Business Logic
    List<WarehouseResponseDTO> findWarehousesByName(String name);
    List<WarehouseResponseDTO> findWarehouseByCapacity(Integer minimumCapacity, Integer maximumCapacity);
}
