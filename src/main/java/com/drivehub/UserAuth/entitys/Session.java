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
import java.util.HashMap;
import java.util.Map;

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
        //generate claim data from token
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", getUserId());
        claims.put("isAdmin", isAdmin());
        //way to create access token
        String token = Jwts.builder()
                .setId(String.valueOf(getId()))
                .setExpiration(Date.from(getExpiredAt()))
                .setIssuedAt(new Date())
                .claim("claims", claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return token;
    }

    public static Map<String, Object> decodeAccessToken(String accessToken, String secret) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(accessToken).getBody();
        Date expireDate = body.getExpiration();
        if(expireDate.compareTo(Date.from(Instant.now())) < 0) {
            throw new Error("Session has expired");
        }
        Map<String, Object> claims = body.get("claims", Map.class);
        return claims;
    }
}
