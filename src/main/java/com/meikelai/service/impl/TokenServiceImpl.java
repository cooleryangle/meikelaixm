package com.meikelai.service.impl;

import com.meikelai.service.ITokenService;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements ITokenService {

    private String secretKey="0d550e761ddc83f3565c2f7b15aa3518";

    public String generateToken(String openId) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + 3600000; // Token 过期时间设置，例如一小时后
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setSubject(openId) // 将 openId 设置为主题
                .setIssuedAt(now) // 设置 Token 的签发时间
                .setExpiration(exp) // 设置过期时间
                .signWith(SignatureAlgorithm.HS512, secretKey) // 使用 HS512 算法和密钥签名 JWT
                .compact();
    }

    public String parseToken(String jwtToken) {
        try {
            // 打印接收到的 Token，检查其格式
            System.out.println("Received JWT Token: " + jwtToken);

            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwtToken);
            return claims.getBody().getSubject();
        } catch (MalformedJwtException e) {
            // 打印异常信息和 Token
            System.out.println("Malformed JWT Token: " + jwtToken);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            // 打印其他异常信息
            e.printStackTrace();
            return null;
        }
    }
}
