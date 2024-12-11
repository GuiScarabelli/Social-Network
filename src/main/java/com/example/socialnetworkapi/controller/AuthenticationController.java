package com.example.socialnetworkapi.controller;

import com.example.socialnetworkapi.dto.login.LoginResponseDto;
import com.example.socialnetworkapi.dto.auth.AuthenticationDto;
import com.example.socialnetworkapi.dto.auth.RegisterDto;
import com.example.socialnetworkapi.entity.User;
import com.example.socialnetworkapi.services.TokenService;
import com.example.socialnetworkapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository repository;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody @Validated AuthenticationDto data){
    var usernamePass = new UsernamePasswordAuthenticationToken(data.login(), data.password());
    var auth = this.authenticationManager.authenticate(usernamePass);
    var token = tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDto(token));
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody @Validated RegisterDto data){

    if (this.repository.findByLogin(data.login()) != null) {
      return ResponseEntity.badRequest().build();
    }

    String encryptedPass = new BCryptPasswordEncoder().encode(data.password());
    User user = new User(data.login(), data.email(), encryptedPass, data.role());

   return ResponseEntity.ok().body(repository.save(user));
  }
}
