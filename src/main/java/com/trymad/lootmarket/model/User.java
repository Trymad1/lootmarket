package com.trymad.lootmarket.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    private UUID id;
    
    private String name;
    private String mail;
    private String password;
    private boolean placeService;
    private boolean placeReview;
    private boolean useService;
    private LocalDateTime registationDate;
    private LocalDateTime lastEnter;
    private LocalDateTime lastUpdate;

}
