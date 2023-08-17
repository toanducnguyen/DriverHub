package com.drivehub.Product.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String category;

    @Column
    private String brand;

    @Column
    private long price;

    public Products(String name, String category, String brand, Long price) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
    }
}
