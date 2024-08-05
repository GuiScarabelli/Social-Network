package com.example.socialmedia.service;

import com.example.socialmedia.dto.LoginRequest;
import com.example.socialmedia.dto.LoginResponse;
import com.example.socialmedia.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class TokenService {

  private final JwtEncoder encoder;
  private final UserRepository repository;
  private BCryptPasswordEncoder passwordEncoder;

  public LoginResponse login(LoginRequest request){
    var user = repository.findByUsername(request.username());
    if (user.isEmpty() || !user.get().isLoginCorrect(request, passwordEncoder) ){
      throw new BadCredentialsException("User or password is invalid!");
    }

    var claim = JwtClaimsSet.builder()
            .issuer("social-network-api")
            .subject(user.get().getUserId().toString())
            .expiresAt(Instant.now().plusSeconds(300L))
            .issuedAt(Instant.now())
            .build();

    var jwtValue = encoder.encode(JwtEncoderParameters.from(claim)).getTokenValue();

    return new LoginResponse(jwtValue, 300L);
  }
}
