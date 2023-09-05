package com.drivehub.CartList.repositorys;

import com.drivehub.CartList.entities.CartList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartListRepository extends JpaRepository<CartList,Long> {

    CartList findByUserIdAndProductId(long userId,long productId);

    List<CartList> findAllByUserId(long userId);
}
