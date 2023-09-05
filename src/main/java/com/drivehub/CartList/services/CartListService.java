package com.drivehub.CartList.services;

import com.drivehub.CartList.entities.statusEnum;
import com.drivehub.Product.entitys.Products;
import com.drivehub.Product.repositorys.ProductRepository;
import com.drivehub.CartList.entities.CartList;
import com.drivehub.CartList.repositorys.CartListRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartListService {
    private CartListRepository cartListRepository;
    private ProductRepository productRepository;

    public void addToCartList(long userId,long id){
        if (productRepository.findById(id).isEmpty()){
            throw new Error("loi");
        }
        CartList cartList =new CartList(userId,id, statusEnum.Pending);
        cartListRepository.save(cartList);
    }
    public void deleteCartList(long userId, long productId){
        if (productRepository.findById(productId).isEmpty()){
            throw new Error("loi");
        }
        CartList cartList = cartListRepository.findByUserIdAndProductId(userId,productId);
        cartListRepository.delete(cartList);
    }

    public List<Products> showCartList(long userId,int page, int  size) {
        Pageable pageRequest = PageRequest.of(page, size);
        List<CartList> cartLists = cartListRepository.findAllByUserId(userId);
        if (cartLists.isEmpty()){
            throw new Error("empty cart list");
        }
        List<Products> products = new ArrayList<>();
        for (CartList c :cartLists
             ) {
            Optional<Products> productOpt = productRepository.findById(c.getProductId());
            if (productOpt.isEmpty()){
                throw  new Error("empty product");
            }
            Products product = productOpt.get();
            products.add(product);
        }

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), products.size());
        List<Products> pageContent = products.subList(start, end);
        return pageContent;
    }
}
