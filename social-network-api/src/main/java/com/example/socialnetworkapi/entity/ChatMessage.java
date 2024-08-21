package com.example.socialnetworkapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatMessage {

  @Id
  @UuidGenerator
  private String Id;

  private String chatId;

  private String recipientId;

  private String senderId;

  private String content;

  private Date timestamp;
}
