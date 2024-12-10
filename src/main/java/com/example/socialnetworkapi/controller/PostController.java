package com.example.socialnetworkapi.controller;

import com.example.socialnetworkapi.dto.post.PostDto;
import com.example.socialnetworkapi.entity.Post;
import com.example.socialnetworkapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

