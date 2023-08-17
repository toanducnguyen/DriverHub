package com.drivehub.UserInfo.services;

import com.drivehub.UserInfo.entitys.UserInfo;
import com.drivehub.UserInfo.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserInfoService {
    private UserInfoRepository userInfoRepository;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Input{
        private String userName;
        private String phoneNumber;
        private  String address;
    }

    public void createUserInfo(long userLoginId){
        UserInfo userInfo = new UserInfo(userLoginId,null, null, null);
        userInfoRepository.save(userInfo);
    }

    public void updateUserInfo(Input input,long userId){
        Optional<UserInfo> userInfoOpt = userInfoRepository.findById(userId);
        if(userInfoOpt.isEmpty()) {
            throw new Error("not found user info");
        }
        UserInfo userInfo = userInfoOpt.get();
        if(input.userName != null){
            userInfo.setUserName(input.userName);
        }
        if (input.address != null) {
            userInfo.setAddress(input.address);
        }
        if (input.phoneNumber != null){
            userInfo.setPhoneNumber(input.phoneNumber);
        }
        userInfoRepository.save(userInfo);
    }

}
