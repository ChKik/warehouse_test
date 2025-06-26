package com.warehouse_test.mapper;

import com.warehouse_test.dto.ProductResponseDTΟ;
import com.warehouse_test.dto.UpdateProductDTO;
import com.warehouse_test.dto.CreateProductDTO;
import com.warehouse_test.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // to kanw me mia void sunartisi gia na mi ftiaksw kainourgio antikeimeno. Den kanei return tipota para mono update.
    @Mapping(target="id",ignore = true)
    @Mapping(target="createdAt",ignore = true)
    @Mapping(target="updatedAt",ignore = true)
    @Mapping(target="deletedAt",ignore = true)
    @Mapping(target="inventory",ignore = true)
    Product toProductEntity(CreateProductDTO dto);

    // to kanw me mia void sunartisi gia na mi ftiaksw kainourgio antikeimeno. Den kanei return tipota para mono update.
    @Mapping(target="id",ignore = true)
    @Mapping(target="createdAt",ignore = true)
    @Mapping(target="updatedAt",ignore = true)
    @Mapping(target="deletedAt",ignore = true)
    @Mapping(target="inventory",ignore = true)
    void updateProductFromDto(UpdateProductDTO dto, @MappingTarget Product entity);


    ProductResponseDTΟ toResponseDTO(Product product);
}
