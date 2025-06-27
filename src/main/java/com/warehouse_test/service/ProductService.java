package com.warehouse_test.service;

import com.warehouse_test.entity.Product;
import com.warehouse_test.dto.CreateProductDTO;
import com.warehouse_test.dto.ProductResponseDTΟ;
import com.warehouse_test.dto.UpdateProductDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    //CRUD
    List<ProductResponseDTΟ> getProducts();
    Optional<ProductResponseDTΟ> getProductById(long id);
    CreateProductDTO createProduct(CreateProductDTO product);
    UpdateProductDTO updateProduct(long id, UpdateProductDTO product);
    void deleteProduct(long id);

    //Business
    List<ProductResponseDTΟ> getProductsByName(String name);
    List<ProductResponseDTΟ> getProductByWeight(BigDecimal weight);
    List<ProductResponseDTΟ> getProductByCategory(String category);
}
