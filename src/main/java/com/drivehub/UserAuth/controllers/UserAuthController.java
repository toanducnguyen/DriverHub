package com.drivehub.UserAuth.controllers;

import com.drivehub.UserAuth.Input.Input;
import com.drivehub.UserAuth.entitys.Session;
import com.drivehub.UserAuth.services.UserLoginService;
import com.drivehub.UserAuth.services.UserSignUpService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import static com.drivehub.share.SercretKey.secretKey;

@RestController
public class UserAuthController {

    @Autowired
    private UserSignUpService userSignUpService;
    @Autowired
    private UserLoginService userLoginService;


    @PostMapping(value = "/signup")
    public boolean signUp(@RequestBody Input signUpInput){
        return userSignUpService.signUp(signUpInput);
    }

    @PostMapping(value = "/signup/admin")
    public boolean signUpAdmin(@RequestHeader("access-token") String accessToken, @RequestBody Input signUpInput){
        Claims body = Session.decodeAccessToken(accessToken, secretKey);
        return userSignUpService.signUpAdmin(signUpInput);
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody Input loginInput) {
        return userLoginService.login(loginInput);
    }



}
