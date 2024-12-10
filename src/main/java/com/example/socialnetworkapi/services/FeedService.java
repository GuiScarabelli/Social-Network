package com.example.socialnetworkapi.services;

import com.example.socialnetworkapi.dto.FeedDto;
import com.example.socialnetworkapi.dto.FeedItemDto;
import com.example.socialnetworkapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

  @Autowired
  PostRepository repository;

  public FeedDto getAllPosts(int page, int pageSize){
    var posts = repository.findAll(PageRequest
                    .of(page, pageSize, Sort.Direction.DESC, "creationTimestamp"))
            .map(post -> new FeedItemDto(post.getId(),
                    post.getContent(),
                    post.getUser().getUsername()));


    return new FeedDto(posts.getContent(), page, pageSize, posts.getTotalPages(), posts.getTotalElements());
  }
}
