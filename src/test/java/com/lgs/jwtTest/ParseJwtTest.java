package com.lgs.jwtTest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ParseJwtTest {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjMiLCJzdWIiOiLlsI_mooEiLCJpYXQiOjE1ODE0MDU3NzAsImEiOiJh55qE5YC8IiwiYiI6ImLnmoTlgLwifQ.g91oDCFNMakvbw7RvLFOV0VPGFUfOkSXWmzOC5Ht2eA";
        Claims claims = Jwts.parser().setSigningKey("liang").parseClaimsJws(token).getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("issuedAt:"+claims.getIssuedAt());

        System.out.println("a:"+claims.get("a"));
        System.out.println("b:"+claims.get("b"));
    }
}
