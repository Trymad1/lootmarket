package com.trymad.lootmarket.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "users")
public class User {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true, length = 25)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String mail;

    @Column(nullable = false, length = 25)
    private String password;

    @Column(nullable = false)
    private boolean placeService;

    @Column(nullable = false)
    private boolean placeReview;

    @Column(nullable = false)
    private boolean useService;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false)
    private LocalDateTime lastEnter;

    @Column(nullable = false)
    private LocalDateTime lastUpdate;

}
