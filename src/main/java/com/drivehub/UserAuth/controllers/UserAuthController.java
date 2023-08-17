package com.drivehub.UserAuth.controllers;

import com.drivehub.UserAuth.Input.Input;
import com.drivehub.UserAuth.entitys.Session;
import com.drivehub.UserAuth.services.UserLoginService;
import com.drivehub.UserAuth.services.UserSignUpService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.drivehub.share.SercretKey.secretKey;

@RestController
public class UserAuthController {

    @Autowired
    private UserSignUpService userSignUpService;
    @Autowired
    private UserLoginService userLoginService;


    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody Input signUpInput){
        try {
            userSignUpService.signUp(signUpInput);
            return ResponseEntity.ok("Sign up successfully");
        } catch (Throwable e){
            return  ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping(value = "/signup/admin")
    public ResponseEntity<?> signUpAdmin(@RequestHeader("access-token") String accessToken, @RequestBody Input signUpInput){
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, secretKey);
        if(userData.get("isAdmin").equals(true)) {
            return ResponseEntity.ok(userSignUpService.signUpAdmin(signUpInput));
        }
        //not permission to call api (only admin can call this api) (http 403)
        return ResponseEntity.status(403).body("Only admin can call this api");
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody Input loginInput) {
        try{
            return ResponseEntity.ok(userLoginService.login(loginInput));
        }catch (Error e){
            return  ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
