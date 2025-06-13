package com.warehouse_test.service;

import com.warehouse_test.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    //CRUD
    List<Product> getProducts();
    Optional<Product> getProductById(long id);
    Product createProduct(Product product);
    Product updateProduct(long id, Product product);
    void deleteProduct(long id);

    //Business
    List<Product> getProductsByName(String name);
    List<Product> getProductByWeight(BigDecimal weight);
    List<Product> getProductByCategory(String category);
}
