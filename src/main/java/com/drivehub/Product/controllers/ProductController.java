package com.drivehub.Product.controllers;

import com.drivehub.Product.services.ProductService;
import com.drivehub.UserAuth.Input.Input;
import com.drivehub.UserAuth.entitys.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.drivehub.share.SercretKey.secretKey;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/createProduct")
    public ResponseEntity<?> createProduct(@RequestHeader("access-token") String accessToken, @RequestBody ProductService.ProductInput productInput){
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, secretKey);
        if(userData.get("isAdmin").equals(true)) {
            productService.createProduct(productInput);
            return ResponseEntity.ok("create product successfully");
        }
        //not permission to call api (only admin can call this api) (http 403)
        return ResponseEntity.status(403).body("Only admin can call this api");
    }

    @PutMapping(value = "/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@RequestHeader("access-token") String accessToken, @RequestBody ProductService.ProductInput productInput, @PathVariable long id){
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, secretKey);
        if(userData.get("isAdmin").equals(true)) {
            productService.updateProduct(productInput,id);
            return ResponseEntity.ok("update product successfully");
        }
        //not permission to call api (only admin can call this api) (http 403)
        return ResponseEntity.status(403).body("Only admin can call this api");
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    public  ResponseEntity<?> deleteProduct(@RequestHeader("access-token") String accessToken, @PathVariable long id) {
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, secretKey);
        if (userData.get("isAdmin").equals(true)) {
            productService.deleteProduct(id);
            return ResponseEntity.ok("delete product successfully");
        }
        //not permission to call api (only admin can call this api) (http 403)
        return ResponseEntity.status(403).body("Only admin can call this api");
    }
    @GetMapping(value = "/products")
    public ResponseEntity<?> getListProduct(@RequestBody(required = false) String category,
                                            @RequestParam int page,
                                            @RequestParam int size)
    {
        if (category == null){
            productService.show(page,size);
            return ResponseEntity.ok(productService.show(page,size));
        }
        productService.getByCategory(category,page,size);
        return ResponseEntity.ok(productService.getByCategory(category,page,size));
    }

}
