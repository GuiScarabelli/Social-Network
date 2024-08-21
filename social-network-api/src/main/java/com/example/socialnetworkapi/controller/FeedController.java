package com.example.socialnetworkapi.controller;

import com.example.socialnetworkapi.dto.FeedDto;
import com.example.socialnetworkapi.dto.FeedItemDto;
import com.example.socialnetworkapi.repository.PostRepository;
import com.example.socialnetworkapi.services.FeedService;
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
  private PostRepository postRepository;

  @Autowired
  private FeedService service;

  @GetMapping()
  public ResponseEntity<FeedDto> feed(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "pageSize", defaultValue = "0") int pageSize){

    return ResponseEntity.ok().body(service.getAllPosts(page, pageSize));
  }


}

