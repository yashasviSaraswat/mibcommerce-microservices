package com.mibcommerce.product.model;

import jakarta.persistence.*;
import lombok.Data;

//import java.lang.reflect.Type;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "picture_url")
    private String pictureUrl;

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private Type productType;

    @ManyToOne
    @JoinColumn(name = "product_brand_id", nullable = false)
    private Brand productBrand;
}