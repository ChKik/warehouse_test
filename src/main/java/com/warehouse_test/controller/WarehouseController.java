package com.warehouse_test.controller;

import com.warehouse_test.entity.Warehouse;
import com.warehouse_test.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/warehouses")
@CrossOrigin(origins="*")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> findAllWarehouses() {
        List<Warehouse> warehouses=warehouseService.findAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findWarehouseById(@PathVariable long id) {
        return warehouseService.findWarehouseById(id)
                .map(warehouse -> ResponseEntity.ok(warehouse))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse createdWarehouse=warehouseService.createWarehouse(warehouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWarehouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable long id, @RequestBody Warehouse warehouse) {
        try{
            Warehouse updatedWarehouse=warehouseService.updateWarehouse(id, warehouse);
            return ResponseEntity.ok(updatedWarehouse);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable long id) {
        try{
            warehouseService.deleteWarehouse(id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Warehouse>> findWarehousesByName(@RequestParam(required = false) String name) {
        //checkarei oti to name den einai null kai an einai null tote epistrefei oles tis apithikes
        if (name == null || name.isBlank()) {
            return ResponseEntity.ok(warehouseService.findAllWarehouses());
        }
        return ResponseEntity.ok(warehouseService.findWarehousesByName(name));
    }

}

