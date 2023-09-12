package com.quizmaker.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "d2e4b4922e5b10e8ba2032afe5e4bcbb8751ab09a743c290041601ab5c94ec62";
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject );
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000 ))
            .signWith(getSigninKey(), SignatureAlgorithm.HS256)
            .compact();

    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiretion(token).before(new Date());
    }

    private Date extractExpiretion(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSigninKey() {
         byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
         return Keys.hmacShaKeyFor(keyByte);
    }
}
