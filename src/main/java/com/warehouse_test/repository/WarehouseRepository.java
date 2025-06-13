package com.warehouse_test.repository;

import com.warehouse_test.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {
    List<Warehouse> findByWarehouseName(String warehouseName);
    List<Warehouse> findByCapacityGreaterThan(Integer capacity);

    @Query(" SELECT w FROM Warehouse w WHERE w.managerName IS NOT NULL ") //kanei return olo to object
    List<Warehouse> findWarehousesManagers();

    @Query("SELECT x FROM Warehouse x WHERE x.capacity > 200 ")
    List<Warehouse> findLargeCapacityWarehouses();

    @Query("SELECT w FROM Warehouse w WHERE w.capacity > :minCapacity")
    List<Warehouse> findByCapacityGreaterThan(@Param("minCapacity") int minCapacity); //toy pernas san parametro ena noumero

    @Query("SELECT w FROM Warehouse w WHERE w.capacity BETWEEN :minCap AND :maxCap")
    List<Warehouse> findWarehouseCapacityRange(@Param("minCap") int minCap, @Param("maxCap") int maxCap);
}
