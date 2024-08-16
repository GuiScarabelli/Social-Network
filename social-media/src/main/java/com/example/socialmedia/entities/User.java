package com.example.socialmedia.entities;

import com.example.socialmedia.dto.LoginRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter @Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID userId;

  @Column(unique = true)
  private String username;

  private String password;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
          name = "tb_users_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
  )

  private Set<Role> roles;

  public boolean isLoginCorrect(LoginRequest request, PasswordEncoder encoder) {
    return encoder.matches(request.password(), this.password);
  }
}
