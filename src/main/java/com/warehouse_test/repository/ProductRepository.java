package com.warehouse_test.repository;

import com.warehouse_test.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query
    List<Product> findByName(String name);

    @Query
    List<Product> findByCategory(String category);

    @Query
    List<Product> findByWeight(BigDecimal weight);

    @Query("SELECT p FROM Product p WHERE p.price> :minPrice AND p.price< :maxPrice")
    List<Product> findProductByPrice(@Param("minPrice" )BigDecimal minPrice,@Param("maxPrice")BigDecimal maxPrice);

    @Query("SELECT DISTINCT p.category FROM Product p ")
    List<String> getAllCategories();  //mas gurnaei ena string me categories.

}
