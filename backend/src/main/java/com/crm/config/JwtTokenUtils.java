package com.crm.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.token.time}")
    private String tokenExpirationTime;

    @Value("${jwt.expiration.refreshToken.time}")
    private String refreshTokenExpirationTime;

    public String generateToken(final UserDetails userDetails) {
        return doGenerate(tokenExpirationTime, userDetails);
    }

    public String generateRefreshToken(final UserDetails userDetails) {
        return doGenerate(refreshTokenExpirationTime, userDetails);
    }

    public Boolean isValid(final String token, final UserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String getUsername(final String token) {
        return getClaim(token, Claims::getSubject);
    }

    private String doGenerate(final String expirationTime, final UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Integer.parseInt(expirationTime) * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date getExpirationDate(final String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private <T> T getClaim(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(final String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(final String token) {
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }

}
