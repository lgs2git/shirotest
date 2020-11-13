package com.lgs.jwtTest;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TestJwt {
    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder().setId("123")
                .setSubject("小梁")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "liang")
                .claim("a","a的值")
                .claim("b","b的值");

        String token = jwtBuilder.compact();
        System.out.println(token);
    }
}
