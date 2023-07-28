package com.drivehub.UserAuth.entitys;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.persistence.*;
import jakarta.xml.bind.DatatypeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Entity
@Table()
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private boolean isAdmin;
    private Instant expiredAt;

    public Session(long userId, boolean isAdmin, Instant expiredAt) {
        this.userId = userId;
        this.isAdmin = isAdmin;
        this.expiredAt = expiredAt;
    }

    public String genAccessToken(String secret){
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
        //way to create access token
        JwtBuilder builder = Jwts.builder()
                .setId(String.valueOf(getId()))
                .setExpiration(Date.from(getExpiredAt()))
                .setIssuedAt(new Date())
//                .claim("data", new String[]{String.valueOf(getUserId()), String.valueOf(isAdmin())})
                .claim("data", getUserId())
                .signWith(SignatureAlgorithm.HS512, signingKey);
        String token = builder.compact();
        return "hhihi";
    }

    public static Claims decodeAccessToken(String accessToken, String secret) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(accessToken).getBody();
        return body;
    }
}
