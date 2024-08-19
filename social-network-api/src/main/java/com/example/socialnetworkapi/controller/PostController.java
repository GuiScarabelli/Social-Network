package com.example.socialnetworkapi.controller;

import com.example.socialnetworkapi.dto.auth.AuthenticationDto;
import com.example.socialnetworkapi.dto.post.PostDto;
import com.example.socialnetworkapi.entity.Post;
import com.example.socialnetworkapi.entity.User;
import com.example.socialnetworkapi.infra.security.SecurityFilter;
import com.example.socialnetworkapi.repository.PostRepository;
import com.example.socialnetworkapi.repository.UserRepository;
import com.example.socialnetworkapi.services.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.annotations.NotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
@RequestMapping("/posts")
public class PostController {

  @Autowired
  private PostRepository repository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

  private String token;
  @PostMapping()
  public ResponseEntity<Post> post(@RequestBody PostDto dto, @RequestHeader("Authorization") String auth){

    this.token = tokenService.validateToken(auth.replace("Bearer ", ""));
    var user = tokenService.recoverUserInfo(token);

    if(user.isPresent()){
      var post = new Post();
      post.setContent(dto.content());
      post.setUser(user.get());
      return ResponseEntity.ok().body(repository.save(post));
    }
    return ResponseEntity.badRequest().build();
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost(@PathVariable String id, @RequestHeader("Authorization") String auth){

    Optional<Post> post = Optional.ofNullable(repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));



    if(post.get().getUser().equals(token)){
      repository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

}
