package com.warehouse_test.mapper;

import com.warehouse_test.dto.CreateWarehouseDTO;
import com.warehouse_test.dto.UpdateWarehouseDTO;
import com.warehouse_test.dto.WarehouseResponseDTO;
import com.warehouse_test.entity.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    @Mapping(target="id",ignore = true)
    @Mapping(target="createdAt",ignore = true)
    @Mapping(target="updatedAt",ignore = true)
    @Mapping(target="deletedAt",ignore = true)
    @Mapping(target="inventory",ignore = true)
    Warehouse toEntity(CreateWarehouseDTO dto);



    // to kanw me mia void sunartisi gia na mi ftiaksw kainourgio antikeimeno. Den kanei return tipota para mono update.
    @Mapping(target="id",ignore = true)
    @Mapping(target="createdAt",ignore = true)
    @Mapping(target="updatedAt",ignore = true)
    @Mapping(target="deletedAt",ignore = true)
    @Mapping(target="inventory",ignore = true)
    void updateWarehouseFromDto(UpdateWarehouseDTO dto, @MappingTarget Warehouse entity);

    //Sto response kanei automatically map ta matching fields: id, warehouseName, address, managerName, capacity, createdAt, updatedAt, deletedAt.
    WarehouseResponseDTO toResponseDTO(Warehouse warehouse);
}
