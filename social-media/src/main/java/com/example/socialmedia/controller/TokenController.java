package com.example.socialmedia.controller;

import com.example.socialmedia.dto.LoginRequest;
import com.example.socialmedia.dto.LoginResponse;
import com.example.socialmedia.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TokenController {

  private final JwtEncoder jwtEncoder;

  @Autowired
  private TokenService service;

  @PostMapping('/login')
  public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest request){


  }

}
