package com.lyb.lyblog.utils;

import java.util.Calendar;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtils {
    private static String SIGNATURE = "loyalBlog";
    private static int EXPIRES_IN = 24 * 3600; // 过期时间1天

    public static String getToken(Long id, String username) {
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("id", id);
        builder.withClaim("username", username);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, EXPIRES_IN);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(SIGNATURE));
    }

    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }

    public static Map<String, Claim> getPayload(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
        return jwt.getClaims();
    }
}
