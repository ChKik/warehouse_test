package com.warehouse_test.service;

import com.warehouse_test.entity.Inventory;
import com.warehouse_test.repository.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> getInventoryById(long id) {
        try {
            return inventoryRepository.findById((int) id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid inventory ID format");
        }
    }

    @Override
    public Inventory createInventory(Inventory inventory) {

        if (inventory.getWarehouse() == null || inventory.getProduct() == null) {
            throw new IllegalArgumentException("Warehouse and Product must not be null");
        }

        if (inventory.getMinimumStock() == 0) inventory.setMinimumStock(20);
        if (inventory.getMaximumStock() == 0) inventory.setMaximumStock(2000);

        if (inventory.getQuantity() < inventory.getMinimumStock() ||
                inventory.getQuantity() > inventory.getMaximumStock()) {
            throw new IllegalArgumentException("Quantity must be between minimum and maximum stock levels");
        }

        return inventoryRepository.save(inventory);
    }


    @Override
    public Inventory updateInventory(long id, Inventory inventory) {
        Inventory existing = inventoryRepository.findById((int) id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with ID: " + id));


        existing.setQuantity(inventory.getQuantity());
        existing.setMinimumStock(inventory.getMinimumStock());
        existing.setMaximumStock(inventory.getMaximumStock());
        // lastRestocked updated automatically apo to  @PostUpdate


        if (existing.getQuantity() < existing.getMinimumStock() ||
                existing.getQuantity() > existing.getMaximumStock()) {
            throw new IllegalArgumentException("Quantity must be between minimum and maximum stock levels");
        }

        return inventoryRepository.save(existing);
    }

    @Override
    public void deleteInventory(long id) {
        if (!inventoryRepository.existsById((int) id)) {
            throw new EntityNotFoundException("Inventory not found with ID: " + id);
        }
        inventoryRepository.deleteById((int) id);
    }


    @Override
    public List<Inventory> getInventoryByProductQuantity(long productId, long quantity) {
        return inventoryRepository.getInventoryByProductQuantity((int) productId, (int) quantity);
    }

    @Override
    public List<Inventory> getProductByWarehouseId(long warehouseId) {
        return inventoryRepository.findByWarehouseId(warehouseId);
    }


}