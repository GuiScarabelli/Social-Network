package com.example.socialnetworkapi.repository;

import com.example.socialnetworkapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
  UserDetails findByLogin(String login);
}
