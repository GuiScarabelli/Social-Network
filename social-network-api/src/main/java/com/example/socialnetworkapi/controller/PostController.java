package com.example.socialnetworkapi.controller;

import com.example.socialnetworkapi.dto.auth.AuthenticationDto;
import com.example.socialnetworkapi.dto.post.PostDto;
import com.example.socialnetworkapi.entity.Post;
import com.example.socialnetworkapi.entity.User;
import com.example.socialnetworkapi.enums.Role;
import com.example.socialnetworkapi.infra.security.SecurityFilter;
import com.example.socialnetworkapi.repository.PostRepository;
import com.example.socialnetworkapi.repository.UserRepository;
import com.example.socialnetworkapi.services.PostService;
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
  private PostService service;

  @PostMapping()
  public ResponseEntity<Post> post(@RequestBody PostDto dto, @RequestHeader("Authorization") String auth){
    return ResponseEntity.status(201).body(service.createPost(dto, auth));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost(@PathVariable String id, @RequestHeader("Authorization") String auth){
    service.deletePost(id, auth);
    return ResponseEntity.noContent().build();
    }
}

