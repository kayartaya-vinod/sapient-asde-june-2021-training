package com.sapient.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;
    private String category;
    private String brand;
    private String picture;
    @Column(name = "unit_price")
    private Double unitPrice;
    private Double discount;
}