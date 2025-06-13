package com.warehouse_test.repository;

import com.warehouse_test.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
    List<Inventory> findByWarehouseId(Long warehouseId);

    Optional<Inventory> findByWarehouseIdAndProductId(Long warehouseId, Long productId);

    @Query("SELECT i FROM Inventory i WHERE i.minimumStock < 20")
    List<Inventory> findMinimumStockWarehouses(); //mas dinei ola ta object apothikes poy exoyn minimum stock.

    @Query("SELECT i FROM Inventory i WHERE i.product=:prod_id and i.quantity=:prod_quantity")
    List<Inventory> getInventoryByProductQuantity(@Param("prod_id") int prod_id, @Param("prod_quantity") int prod_quantity);

}
