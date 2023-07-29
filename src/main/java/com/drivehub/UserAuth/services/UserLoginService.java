package com.drivehub.UserAuth.services;

import com.drivehub.UserAuth.Input.Input;
import com.drivehub.UserAuth.entitys.Session;
import com.drivehub.UserAuth.entitys.UserAuth;
import com.drivehub.UserAuth.repositorys.UserAuthRepository;
import jakarta.xml.bind.DatatypeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.drivehub.share.SercretKey.secretKey;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginService {
    @Autowired
    private UserAuthRepository userLoginRepository;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Output {
        public String accessToken;
        public long userId;
    }


    public Output login(Input loginInput){
        String hashedPassword = hashPassword(loginInput.getPassword());
        UserAuth userAuth = userLoginRepository.findByEmail(loginInput.getEmail());
        if(userAuth == null){
            throw new Error("Wrong email or password");
        }
        if (hashedPassword.equals(userAuth.getPassword())){
            Session session = new Session(userAuth.getId(), userAuth.isAdmin(), Instant.now().plus(2,  ChronoUnit.HOURS));
            String accessToken = session.genAccessToken(secretKey);
            return new Output(accessToken, userAuth.getId());
        } else {
            throw new Error("Wrong email or password");
        }
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
