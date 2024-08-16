package com.example.socialnetworkapi.repository;

import com.example.socialnetworkapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
