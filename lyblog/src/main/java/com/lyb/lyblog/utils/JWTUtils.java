package com.lyb.lyblog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.util.Map;

public class JWTUtils {
    private static final String SIGNATURE = "loyalBlog";
    private static final Integer ACCESS_TOKEN_EXPIRES_IN = 2; // 访问令牌过期时间1小时
    private static final Integer REFRESH_TOKEN_EXPIRES_IN = 3600 * 24; // 刷新令牌过期时间1天

    /**
     * 获取访问令牌.
     * 
     * @param id       用户ID
     * @param username 用户名
     * @return 生成的访问令牌
     */
    public static String getAccessToken(Integer id, String username) {
        return buildToken(id, username, ACCESS_TOKEN_EXPIRES_IN);
    }

    /**
     * 获取刷新令牌.
     * 
     * @param id       用户ID
     * @param username 用户名
     * @return 生成的刷新令牌
     */
    public static String getRefreshToken(Integer id, String username) {
        return buildToken(id, username, REFRESH_TOKEN_EXPIRES_IN);
    }

    private static String buildToken(Integer id, String username, Integer expiresIn) {
        Instant expiryDate = Instant.now().plusSeconds(expiresIn);
        return JWT.create()
                .withClaim("id", id)
                .withClaim("username", username)
                .withExpiresAt(java.util.Date.from(expiryDate))
                .sign(Algorithm.HMAC256(SIGNATURE));
    }

    public static void verifyToken(String token) {
        JWT.require(Algorithm.HMAC256(SIGNATURE))
                .build()
                .verify(token); // 验证时自动处理过期时间
    }

    /**
     * 获取Token中的载荷.
     * 
     * @param token JWT令牌
     * @return 载荷信息
     */
    public static Map<String, Claim> getPayload(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
        return jwt.getClaims();
    }
}