package com.drivehub.Product.entitys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCriteria {
    private String name;
    private String category;
    private String brand;
    private Long minPrice;
    private Long maxPrice;

}
