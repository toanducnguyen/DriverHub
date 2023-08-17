package com.drivehub.Product.services;

import com.drivehub.Product.repositorys.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

}
