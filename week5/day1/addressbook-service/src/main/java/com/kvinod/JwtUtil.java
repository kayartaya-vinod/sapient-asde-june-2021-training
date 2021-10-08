package com.kvinod;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    private JwtUtil() {
    }

    private static String SECRET_KEY = "25S_</x^abhsu%?z6&PVxj:Vc~gf9=<$F>KhP9@wZ$Q<{[D=";

    public static String createToken(String userId) throws Exception {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create().withClaim("userId", userId).withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000)).sign(algorithm);

    }

    public static String verify(String token) throws Exception {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build(); // Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("userId").asString();
    }
}
