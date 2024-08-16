package com.example.socialnetworkapi.services;

import com.example.socialnetworkapi.dto.post.PostDto;
import com.example.socialnetworkapi.entity.Post;
import com.example.socialnetworkapi.entity.User;
import com.example.socialnetworkapi.repository.PostRepository;
import com.example.socialnetworkapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

  @Autowired
  private PostRepository repository;

  @Autowired
  private UserRepository userRepository;

  public Post createPost(PostDto dto, JwtAuthenticationToken token){

    UserDetails user = userRepository.findByLogin(token.getName());
    var post = new Post();
      post.setUser((User) user);
      post.setContent(dto.content());
      repository.save(post);
    return post;
  }
}
