package com.warehouse_test.service;

import com.warehouse_test.dto.CreateInventoryDTO;
import com.warehouse_test.dto.InventoryResponseDTO;
import com.warehouse_test.dto.UpdateInventoryDTO;
import com.warehouse_test.entity.Inventory;
import com.warehouse_test.entity.Product;
import com.warehouse_test.entity.Warehouse;
import com.warehouse_test.mapper.InventoryMapper;
import com.warehouse_test.repository.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final ProductService productService;
    private final WarehouseService warehouseService;

    public InventoryServiceImpl(InventoryRepository inventoryRepository,
                                InventoryMapper inventoryMapper,
                                ProductService productService,
                                WarehouseService warehouseService) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
        this.productService = productService;
        this.warehouseService = warehouseService;
    }

    @Override
    public List<InventoryResponseDTO> getInventory() {
        return inventoryRepository.findAll().stream()
                .map(inventoryMapper::toResponseDTO)
                .toList();
    }

    @Override
    public Optional<InventoryResponseDTO> getInventoryById(long id) {
        try {
            return inventoryRepository.findById((int) id)
                    .map(inventoryMapper::toResponseDTO);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid inventory ID format");
        }
    }

    @Override
    public CreateInventoryDTO createInventory(CreateInventoryDTO dto) {
        Warehouse warehouse = warehouseService.findWarehouseById(dto.warehouseId())
                .orElseThrow(() -> new EntityNotFoundException("Warehouse not found with ID: " + dto.warehouseId()));

        Product product = productService.getProductById(dto.productId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + dto.productId()));

        Inventory inventory = inventoryMapper.toEntity(dto, warehouse, product);

        // Set defaults if needed
        if (inventory.getMinimumStock() == 0) {
            inventory.setMinimumStock(20);
        }
        if (inventory.getMaximumStock() == 0) {
            inventory.setMaximumStock(2000);
        }

        // Validate stock levels
        if (inventory.getQuantity() < inventory.getMinimumStock() ||
                inventory.getQuantity() > inventory.getMaximumStock()) {
            throw new IllegalArgumentException("Quantity must be between minimum and maximum stock levels");
        }

        inventoryRepository.save(inventory);
        return dto;
    }

    @Override
    public UpdateInventoryDTO updateInventory(long id, UpdateInventoryDTO dto) {
        Inventory existing = inventoryRepository.findById((int) id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with ID: " + id));

        inventoryMapper.updateInventoryFromDto(dto, existing);

        // Validate stock levels
        if (existing.getQuantity() < existing.getMinimumStock() ||
                existing.getQuantity() > existing.getMaximumStock()) {
            throw new IllegalArgumentException("Quantity must be between minimum and maximum stock levels");
        }

        inventoryRepository.save(existing);
        return dto;
    }

    @Override
    public void deleteInventory(long id) {
        if (!inventoryRepository.existsById((int) id)) {
            throw new EntityNotFoundException("Inventory not found with ID: " + id);
        }
        inventoryRepository.deleteById((int) id);
    }

    @Override
    public List<InventoryResponseDTO> getInventoryByProductQuantity(long productId, long quantity) {
        return inventoryRepository.getInventoryByProductQuantity((int) productId, (int) quantity)
                .stream()
                .map(inventoryMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<InventoryResponseDTO> getProductByWarehouseId(long warehouseId) {

        warehouseService.findWarehouseById(warehouseId)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse not found with ID: " + warehouseId));


        List<Inventory> inventoryItems = inventoryRepository.findByWarehouseId(warehouseId);

        if (inventoryItems.isEmpty()) {
            throw new EntityNotFoundException("No products found in warehouse with ID: " + warehouseId);
        }

        // Convert to DTOs and return
        return inventoryItems.stream()
                .map(inventoryMapper::toResponseDTO)
                .toList();
    }
}