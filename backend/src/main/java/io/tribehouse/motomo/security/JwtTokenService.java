package io.tribehouse.motomo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {
    private final Algorithm hmac512;
    private final JWTVerifier jwtVerifier;

    @Value("${jwt.expiration}")
    private int JWT_TOKEN_EXPIRATION_TIME;

    public JwtTokenService(@Value("${jwt.secret}") final String secret) {
        this.hmac512 = Algorithm.HMAC512(secret);
        this.jwtVerifier = JWT.require(this.hmac512).build();
    }

    public String generateToken(final UserDetails userDetails) {
        return JWT.create().withSubject(userDetails.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_TIME)).sign(this.hmac512);
    }

    public String validateTokenAndGetEmail(final String token) {
        try {
            return jwtVerifier.verify(token).getSubject();
        } catch (final JWTVerificationException verificationEx) {
            //TODO warn not valid
            return null;
        }
    }

}
