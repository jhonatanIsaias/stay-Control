package com.company.account.model.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.company.account.model.entities.Company;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(Company company){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("account")
                    .withSubject(company.getName())
                    .withExpiresAt(tokenExpireTime())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException exception){
                throw new RuntimeException("error create token" + exception.getMessage());
        }
    }

    private Instant tokenExpireTime(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
