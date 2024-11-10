package com.trymad.lootmarket.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne(cascade = CascadeType.MERGE)
    // @JoinColumn(name = "sender_id", nullable = false)
    private UUID senderId;

    // @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    // @JoinColumn(name = "recipient_id", nullable = false)
    private UUID recipientId;

    private String messageText;

    private LocalDateTime sendDate;

}
