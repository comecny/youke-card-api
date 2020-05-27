package com.youke.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JWTUtils {


    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 24*60*60*1000;

    /**
     * 私钥
     */
    private static final String TOKEN_SECRET = "YoUKe_-+202005272142HFOPAIUJMI[Q11KBOG;,S";

    /**
     * 生成jwt签名
     * @param userId
     * @param username
     * @return
     */
    public static String jwtSign(String userId, String username){
        Date Date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        Map headerMap = new HashMap<>();
        headerMap.put("alg","HS256");
        headerMap.put("typ","JWT");
        String sign = JWT.create()
                .withClaim("userId", userId)
                .withClaim("username", username)
                .withHeader(headerMap)
                .withExpiresAt(Date).sign(algorithm);
        return sign;
    }

    /**
     * 验证token是否通过
     * @param token
     * @return
     */
    public static boolean verity(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
    
    public static String decode(String token){
         return JWT.decode(token).getClaim("userId").asString();
    }
}
