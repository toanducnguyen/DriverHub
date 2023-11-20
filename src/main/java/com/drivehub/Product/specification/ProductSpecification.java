package com.drivehub.Product.specification;

import com.drivehub.Product.entitys.ProductCriteria;
import com.drivehub.Product.entitys.Products;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Products> toSpecification(ProductCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> listPredicate = new ArrayList<>();
            if (criteria.getName() != null) {
                listPredicate.add(cb.like(root.get("name"), criteria.getName()));
            }
            if (criteria.getCategory() != null) {
                listPredicate.add(cb.equal(root.get("category"), criteria.getCategory()));
            }
            if (criteria.getBrand() != null) {
                listPredicate.add(cb.equal(root.get("brand"), criteria.getBrand()));
            }
            if (criteria.getMinPrice() != null && criteria.getMaxPrice() != null) {
                listPredicate.add(
                        cb.and(
                                cb.gt(root.get("price"), criteria.getMinPrice()),
                                cb.lt(root.get("price"), criteria.getMaxPrice())
                        )
                );
            }
            return cb.and(listPredicate.toArray(new Predicate[0]));
        };
    }
}