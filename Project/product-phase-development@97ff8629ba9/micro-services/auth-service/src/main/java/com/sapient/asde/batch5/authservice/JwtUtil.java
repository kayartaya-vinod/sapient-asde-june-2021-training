package com.sapient.asde.batch5.authservice;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import java.util.ResourceBundle;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import main.java.com.sapient.asde.batch5.authservice.CipherUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil implements Serializable {

    
    
static String id="userId";
    private JwtUtil() {
    }

    private static String secretKey; 
    static {
        
        ResourceBundle rb = ResourceBundle.getBundle("jwt");
        secretKey= rb.getString("secretKey");
        
    }

    public static String createToken(String userId, String username, String email, String userType) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withClaim(id, userId).withClaim("firstName", username).withClaim("email", email)
                .withClaim("userType", userType).withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000)).sign(algorithm);
    }
    public static String createDayExpireToken(String userId, String username, String email, String userType) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withClaim(id, userId).withClaim("firstName", username).withClaim("email", email)
                .withClaim("userType", userType).withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 24*60* 60 * 1000)).sign(algorithm);
    }

    public static String verify(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build(); 
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim(id).asString();
    }

    public static Map<String, Object> verify2(String token) {
        Map<String, Claim> claims;
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build(); 
        DecodedJWT jwt = verifier.verify(token);
        claims = jwt.getClaims();
        log.info("claims is {}",claims);
        log.info("claims type is {}", claims.getClass());
        Map<String, Object> expectedMap = new HashMap<>();
        for (Entry<String, Claim> entry : claims.entrySet()) {
            if(entry.getKey().equals("firstName") || entry.getKey().equals("email") ){
                expectedMap.put(entry.getKey(), CipherUtil.decrypt(entry.getValue().as(String.class)));
            }
            else{
                expectedMap.put(entry.getKey(), (entry.getValue().as(String.class)));
            }
        }
        return expectedMap;
    }

}
