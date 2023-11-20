package com.drivehub.Product.repositorys;

import com.drivehub.Product.entitys.Products;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long>, JpaSpecificationExecutor<Products> {
    List<Products> findByCategory(String category);

    Optional<Products> findById(long productId);

    List<Products> findBy(Specification<Products> spe);


}
