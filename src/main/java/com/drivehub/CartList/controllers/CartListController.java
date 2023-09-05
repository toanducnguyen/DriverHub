package com.drivehub.CartList.controllers;

import com.drivehub.UserAuth.entitys.Session;
import com.drivehub.CartList.services.CartListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.drivehub.share.SercretKey.secretKey;

@RestController
public class CartListController {
    @Autowired
    private CartListService cartListService;

    @PostMapping(value = "/addToCartList/{id}")
    public ResponseEntity<?> addToCart(@RequestHeader("access-token") String accessToken, @PathVariable long id) {
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, secretKey);
        try {
            cartListService.addToCartList(Long.parseLong(userData.get("userId").toString()),id);
            return ResponseEntity.ok("add successfully");
        }catch (Error e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @DeleteMapping(value = "/deleteCartList/{id}")
    public ResponseEntity<?> deleteCart(@RequestHeader("access-token") String accessToken, @PathVariable long id) {
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, secretKey);
        try {
            cartListService.deleteCartList(Long.parseLong(userData.get("userId").toString()),id);
            return ResponseEntity.ok("delete successfully");
        }catch (Error e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @GetMapping(value ="/cartList")
    public ResponseEntity<?> showCartList(@RequestHeader("access-token") String accessToken,
                                      @RequestParam int page,
                                      @RequestParam int size) {
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, secretKey);
        try {
            cartListService.showCartList(Long.parseLong(userData.get("userId").toString()),page,size);
            return ResponseEntity.ok(cartListService.showCartList(Long.parseLong(userData.get("userId").toString()),page,size));
        }catch (Error e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
