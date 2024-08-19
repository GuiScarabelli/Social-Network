package com.example.socialnetworkapi.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.socialnetworkapi.entity.User;
import com.example.socialnetworkapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class TokenService {
  @Value("${api.security.token.secret}")
  private  String secret;

  @Autowired
  private UserRepository userRepository;

  public String generateToken(User user){
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String token = JWT.create()
              .withIssuer("social-network-api")
              .withSubject(user.getId())
              .withExpiresAt(genExpirationDate())
              .sign(algorithm);
      return token;
    } catch (JWTCreationException exception){
      throw new RuntimeException("Error while generating the token!", exception);
    }
  }

  public String validateToken(String token){
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
              .withIssuer("social-network-api")
              .build()
              .verify(token)
              .getSubject();
    }catch (JWTVerificationException exception){
      return "";
    }
  }

  private Instant genExpirationDate(){
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }

  public Optional<User> recoverUserInfo(String token){
    return userRepository.findById(token);
  }
}
