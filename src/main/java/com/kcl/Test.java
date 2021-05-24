package com.kcl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名： jwt
 * 包名:    com.kcl
 * 文件名   Test
 * 创建者
 * 创建时间: 2021/5/24 9:38 AM
 * 描述  ${TODO}
 */
public class Test {

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,600);

        String token = JWT.create()
                .withHeader(map)
                .withClaim("userId", "1")
                .withClaim("username", "xiaoming")
                .withExpiresAt(instance.getTime()) //令牌过期时间
                .sign(Algorithm.HMAC256("miyao")); //header+playload+miyao进行HMAC256运算-->sign
        System.out.println(token);

        //验证sign是否正确
        //base64解码header和playload + 系统密钥 + HMAC256 --> sign
        //和用户发送的token进行对比
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("miyao")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MjE4MjIxMDAsInVzZXJJZCI6IjEiLCJ1c2VybmFtZSI6InhpYW9taW5nIn0.Fb_P0izYwcAG5RKPH7J9zwlNCa1UmvMKBa8DfGx-2g8");
        System.out.println(verify.getClaim("userId").asString());
        System.out.println(verify.getClaim("username").asString());

    }
}

