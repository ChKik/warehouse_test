package com.warehouse_test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table (name="product")
public class Product extends BaseEntity {
    @Column(name="name",nullable = false,length = 100)
    private String name;

    @Column(name="price",nullable = false,precision = 19,scale = 2)
    private BigDecimal price;

    @Column(name="category",nullable = false)
    private String category;

    @Column(name="weight",nullable = false,precision = 10,scale = 2)
    private BigDecimal weight;

    @OneToMany(mappedBy = "product",cascade = CascadeType.MERGE,fetch=FetchType.LAZY)
    private List<Inventory> inventory;
}
