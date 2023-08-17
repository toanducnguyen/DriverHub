package com.drivehub.Product.repositorys;

import com.drivehub.Product.entitys.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {
}
