package com.drivehub.UserInfo.controllers;

import com.drivehub.UserAuth.Input.Input;
import com.drivehub.UserAuth.entitys.Session;
import com.drivehub.UserInfo.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.drivehub.share.SercretKey.secretKey;

@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PutMapping(value = "/updateUserInfo")
    public ResponseEntity<?> updateInfo(@RequestHeader("access-token") String accessToken, @RequestBody UserInfoService.Input input){
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, secretKey);
        userInfoService.updateUserInfo(input, Long.parseLong(userData.get("userId").toString()));
        return ResponseEntity.ok("update successfully");
    }
}
