package com.warehouse_test.controller;


import com.warehouse_test.dto.CreateInventoryDTO;
import com.warehouse_test.dto.InventoryResponseDTO;
import com.warehouse_test.dto.UpdateInventoryDTO;
import com.warehouse_test.entity.Inventory;
import com.warehouse_test.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
@CrossOrigin(origins="*")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<InventoryResponseDTO>> getInventory() {
        return ResponseEntity.ok(inventoryService.getInventory());
    }

    public ResponseEntity<InventoryResponseDTO> getInventoryById(long id) {
        return inventoryService.getInventoryById(id)
                .map(inventory-> ResponseEntity.ok(inventory))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CreateInventoryDTO> createInventory(@RequestBody CreateInventoryDTO inventory) {
        CreateInventoryDTO createdInventory = inventoryService.createInventory(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInventory);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateInventoryDTO> updateInventory(@PathVariable long id, @RequestBody UpdateInventoryDTO inventory) {
        try{
            UpdateInventoryDTO updatedInventory = inventoryService.updateInventory(id, inventory);
            return ResponseEntity.ok(updatedInventory);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable long id) {
        try{
            inventoryService.deleteInventory(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/product/{productId}/quantity/{quantity}")
    public ResponseEntity<List<InventoryResponseDTO>> getInventoryByProductQuantity(
            @PathVariable long productId,
            @PathVariable long quantity) {

        List<InventoryResponseDTO> result = inventoryService.getInventoryByProductQuantity(productId, quantity);

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<InventoryResponseDTO>> getProductByWarehouseId(@PathVariable long warehouseId) {
        List<InventoryResponseDTO> result = inventoryService.getProductByWarehouseId(warehouseId);

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

}
