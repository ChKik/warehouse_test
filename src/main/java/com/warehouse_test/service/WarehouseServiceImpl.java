package com.warehouse_test.service;


import com.warehouse_test.entity.Warehouse;
import com.warehouse_test.repository.WarehouseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    //crud
    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<Warehouse> findAllWarehouses(){
        return warehouseRepository.findAll();
    }

    @Override
    public Optional<Warehouse> findWarehouseById(long id){
        return  warehouseRepository.findById(id);
    }
    //den xreiazontai updatedat h createat
    //checkarei ama exoyme hdh to onoma toy warehouse kai meta create ama den yparxei.
    @Override
    public Warehouse createWarehouse(Warehouse warehouse){
        boolean exists = !warehouseRepository.findByWarehouseName(warehouse.getWarehouseName()).isEmpty();
        if (exists) {
            throw new IllegalArgumentException("Warehouse with name '" + warehouse.getWarehouseName() + "' already exists.");
        }

        warehouse.setWarehouseName(warehouse.getWarehouseName());
        warehouse.setCapacity(warehouse.getCapacity());
        warehouse.setManagerName(warehouse.getManagerName());
        warehouse.setAddress(warehouse.getAddress());

        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse updateWarehouse(long id, Warehouse warehouse){
        Warehouse existing = warehouseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse not found with ID: " + id));
        //vazei ta nea fields mesa.

        existing.setWarehouseName(warehouse.getWarehouseName());
        existing.setCapacity(warehouse.getCapacity());
        existing.setManagerName(warehouse.getManagerName());
        existing.setAddress(warehouse.getAddress());
        existing.setCapacity(warehouse.getCapacity());


        return warehouseRepository.save(existing);
    }

    @Override
    public void deleteWarehouse(long id){
        boolean exists = warehouseRepository.findById(id).isPresent();
        if (exists) {
            warehouseRepository.deleteById(id);
        }
        else  {
            throw new EntityNotFoundException("Warehouse with ID: " + id + " not found.");
        }

    }

    //business
    @Override
    public List<Warehouse> findWarehousesByName(String name){
        return warehouseRepository.findByWarehouseName(name);
    }

    //vasizetai sto repo method poy vriskei ena range apothikwn mesa sto capcity range poy dinoyme
    @Override
    public List<Warehouse> findWarehouseByCapacity(Integer minimumCapacity, Integer maximumCapacity){
        return warehouseRepository.findWarehouseCapacityRange(minimumCapacity,maximumCapacity);
    }
}
