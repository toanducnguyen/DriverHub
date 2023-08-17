package com.drivehub.UserAuth.services;

import com.drivehub.UserInfo.services.UserInfoService;
import com.drivehub.UserAuth.Input.Input;
import com.drivehub.UserAuth.entitys.UserAuth;
import com.drivehub.UserAuth.repositorys.UserAuthRepository;
import jakarta.xml.bind.DatatypeConverter;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@Service
public class UserSignUpService {

    private UserAuthRepository userSignUpRepository;
    private  UserInfoService userInfoService;


    public boolean signUp(Input signUpInput) {
        UserAuth userAuth = userSignUpRepository.findByEmail(signUpInput.getEmail());
        if (userAuth != null) {
            throw new Error("Email already existed");
        }
        String hashedPassword = hashPassword(signUpInput.getPassword());
        UserAuth userSignUp = new UserAuth(signUpInput.getEmail(), hashedPassword, false);
        userSignUpRepository.save(userSignUp);
        //todo: create & save user info with userloginId
        userInfoService.createUserInfo(userSignUp.getId());
        return true;
    }

    public boolean signUpAdmin(Input signUpInput){
        String hashedPassword = hashPassword(signUpInput.getPassword());
        UserAuth userLogin = new UserAuth(signUpInput.getEmail(),hashedPassword,true );
        userSignUpRepository.save(userLogin);
        return true;
    }

    private String hashPassword(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
            return myHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("No such algorithm exception");
        }
    }

}
