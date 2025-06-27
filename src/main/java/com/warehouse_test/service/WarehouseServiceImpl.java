package com.warehouse_test.service;

import com.warehouse_test.dto.CreateWarehouseDTO;
import com.warehouse_test.dto.UpdateWarehouseDTO;
import com.warehouse_test.dto.WarehouseResponseDTO;
import com.warehouse_test.entity.Warehouse;
import com.warehouse_test.mapper.WarehouseMapper;
import com.warehouse_test.repository.WarehouseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository,
                                WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public List<WarehouseResponseDTO> findAllWarehouses() {
        return warehouseRepository.findAll().stream()
                .map(warehouseMapper::toResponseDTO)
                .toList();
    }

    @Override
    public Optional<WarehouseResponseDTO> findWarehouseById(long id) {
        return warehouseRepository.findById(id)
                .map(warehouseMapper::toResponseDTO);
    }

    @Override
    public CreateWarehouseDTO createWarehouse(CreateWarehouseDTO dto) {
        boolean exists = !warehouseRepository.findByWarehouseName(dto.warehouseName()).isEmpty();
        if (exists) {
            throw new IllegalArgumentException("Warehouse with name '" + dto.warehouseName() + "' already exists.");
        }

        Warehouse warehouse = warehouseMapper.toEntity(dto);
        warehouseRepository.save(warehouse);
        return dto;
    }

    @Override
    public UpdateWarehouseDTO updateWarehouse(long id, UpdateWarehouseDTO dto) {
        Warehouse existing = warehouseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse not found with ID: " + id));

        warehouseMapper.updateWarehouseFromDto(dto, existing);
        warehouseRepository.save(existing);
        return dto;
    }

    @Override
    public void deleteWarehouse(long id) {
        if (!warehouseRepository.existsById(id)) {
            throw new EntityNotFoundException("Warehouse with ID: " + id + " not found.");
        }
        warehouseRepository.deleteById(id);
    }

    @Override
    public List<WarehouseResponseDTO> findWarehousesByName(String name) {
        return warehouseRepository.findByWarehouseName(name).stream()
                .map(warehouseMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<WarehouseResponseDTO> findWarehouseByCapacity(Integer minimumCapacity, Integer maximumCapacity) {
        return warehouseRepository.findWarehouseCapacityRange(minimumCapacity, maximumCapacity).stream()
                .map(warehouseMapper::toResponseDTO)
                .toList();
    }
}