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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

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
  private Logger logger = LoggerFactory.getLogger(AbstractRequestLoggingFilter.class); // Adding a logger to API Gatewwawy

  @PostMapping("/legal")
  public ResponseEntity<Post> post(@RequestBody PostDto dto){

//    logger.info("Token: "+ token);
    var post = new Post();
    post.setContent(dto.content());

    return ResponseEntity.ok().body(repository.save(post));
  }

}
