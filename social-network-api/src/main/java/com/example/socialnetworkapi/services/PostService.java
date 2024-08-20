package com.example.socialnetworkapi.services;

import com.example.socialnetworkapi.dto.post.PostDto;
import com.example.socialnetworkapi.entity.Post;
import com.example.socialnetworkapi.entity.User;
import com.example.socialnetworkapi.enums.Role;
import com.example.socialnetworkapi.repository.PostRepository;
import com.example.socialnetworkapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

  @Autowired
  private PostRepository repository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

  public Post createPost(PostDto dto, String token){
    Optional<User> user = tokenService.recoverUserInfo(token);

    if (user.isEmpty()){
      throw new ResponseStatusException(HttpStatusCode.valueOf(404));
    }

    var post = new Post();
    post.setContent(dto.content());
    post.setUser(user.get());
    return repository.save(post);
  }

  public void deletePost(String id, String auth){

     var user = tokenService.recoverUserInfo(auth)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

     var post = repository.findById(id)
             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

     var isAdmin = user.getRole().name().equals(Role.ADMIN.name());
     System.out.println(isAdmin);

     if(!isAdmin || !post.getUser().getId().equals(user.getId())){
       throw new ResponseStatusException(HttpStatusCode.valueOf(401));
     }
     repository.deleteById(id);
  }
}
