package com.drivehub.UserInfo.services;

import com.drivehub.UserInfo.entitys.UserInfo;
import com.drivehub.UserInfo.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class UserInfoService {

    private UserInfoRepository userInfoRepository;
    public void createUserInfo(long userLoginId){
        UserInfo userInfo = new UserInfo(userLoginId,null, null, null);
        userInfoRepository.save(userInfo);

    }

}
