package com.example.StudentCourse.JwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtHelper {
    final String secretKey = "qXNWVFZkgaX02uP1XSARmU1MeEBNqxbDuiSQJCpjnK15DgPeqcST8cPGkiiAi3UFp83PM191amunvANgYXZa33Zmnqb7F0Yvqfy8";
    private final long ExpirationTime = 100*60*60*24;

    public String generateToken(Map<String, Object> claims, String subject){

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ExpirationTime * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    public String generateToken(UserDetails userDetails){

        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims,userDetails.getUsername());
    }

    public String extractUsername(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

}
