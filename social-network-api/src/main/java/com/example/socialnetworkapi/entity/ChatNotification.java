package com.example.socialnetworkapi.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ChatNotification {
    @Id
    @UuidGenerator
    private String id;

    private User senderId;

    private String recipientId;

    private String content;
}
