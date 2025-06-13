package com.warehouse_test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
    @Table (name="inventory",
uniqueConstraints = @UniqueConstraint(columnNames = {"warehouse_id","product_id"})) //tha eksasfalizei oti ta fkid mas tha einai unique.
public class Inventory extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "warehouse_id",nullable = false) //tha parei fk to warehouse id
    private Warehouse warehouse;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @Column(name="quantity",nullable = false)
    private int quantity;

    @Column(name="minimumStock",nullable = false)
    private int minimumStock;

    @Column(name="maximumStock",nullable = false)
    private int maximumStock;

    @Column(name="lastRestocked",nullable = false)
    private LocalDateTime lastRestocked;


    @Override
    protected void onUpdate() {
        super.onUpdate();
        this.lastRestocked = LocalDateTime.now();
    }


    @Override //vazei ta predefined values stin vasi gia kathe product.
    protected void onCreate(){
        super.onCreate();
        this.minimumStock=20;
        this.maximumStock=2000;
    }

}
