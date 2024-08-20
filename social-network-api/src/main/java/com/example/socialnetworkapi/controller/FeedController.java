package com.example.socialnetworkapi.controller;

import com.example.socialnetworkapi.dto.FeedDto;
import com.example.socialnetworkapi.dto.FeedItemDto;
import com.example.socialnetworkapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
public class FeedController {

  @Autowired
  PostRepository repository;

  @GetMapping()
  public ResponseEntity<FeedDto> feed(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "pageSize", defaultValue = "0") int pageSize){

    var posts = repository.findAll(PageRequest
                      .of(page, pageSize, Sort.Direction.DESC, "creationTimestamp"))
            .map(post -> new FeedItemDto(post.getId(),
                                          post.getContent(),
                                            post.getUser().getUsername()));

    return ResponseEntity.ok(new FeedDto(posts.getContent(), page, pageSize, posts.getTotalPages(), posts.getTotalElements()));
  }


}

