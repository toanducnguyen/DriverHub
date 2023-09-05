package com.drivehub.Product.repositorys;

import com.drivehub.Product.entitys.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {
    List<Products> findByCategory(String category);

    Optional<Products> findById(long productId);
}
