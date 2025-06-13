package com.warehouse_test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name="warehouse")
public class Warehouse extends BaseEntity {

    @Column (name="warehouse_name",nullable=false,length=100)
    private String warehouseName;

    @Column (name="address",nullable=false,length=100)
    private String address;

    @Column (name="manager_name",nullable=false,length=30)
    private String managerName;

    @Column (name="capacity",nullable = false)
    private Integer capacity;

    @OneToMany(mappedBy = "warehouse",cascade = CascadeType.MERGE,fetch=FetchType.LAZY) //vazw merge gia na kanei mono update
    private List<Inventory> inventory;
}
