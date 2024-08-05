package com.example.socialmedia.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter @Setter
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)

  private UUID roleId;

  private String name;
  public enum Values {

    ADMIN(1L),
    BASIC(2L);

    long roleId;

    Values(long roleId) {
      this.roleId = roleId;
    }

    public long getRoleId() {
      return roleId;
    }
  }
}
