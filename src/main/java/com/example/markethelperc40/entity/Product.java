package com.example.markethelperc40.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToOne
    private Brand brand;
    @OneToOne
    private Category category;
    @OneToOne
    private Market market;
    private long quantity;
    @Enumerated(EnumType.ORDINAL)
    private Unit unit;
    private BigDecimal price;
    private String description;
    private double discountCoefficient;
    private LocalDateTime updateTime;

}
