package com.api.ecommerce.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.ecommerce.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {
  @Value("${api.security.token.secret}")
  private String secret;
  
  public String generateToken(User user){
    try {
      Algorithm algorithm = Algorithm.HMAC256(this.secret);
      String token = JWT.create()
        .withIssuer("auth0")
        .withSubject(user.getEmail())
        .withExpiresAt(genExpirationData())
        .sign(algorithm);
      return token;
    } catch (JWTCreationException e) {
      throw new RuntimeException("Error while generating token", e);
    }
  }

  private Instant genExpirationData(){
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("+01:00"));
  }

  public String validateToken(String token){
    try {
      Algorithm algorithm = Algorithm.HMAC256(this.secret);
      return JWT.require(algorithm)
        .withIssuer("auth0")
        .build()
        .verify(token)
        .getSubject();
      
    } catch (JWTVerificationException e) {
      return "";
    }
  }
}