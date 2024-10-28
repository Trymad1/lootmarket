package com.trymad.lootmarket.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private UUID id;
    private String name;
    private String mail;
    private String password;
    private boolean place_service;
    private boolean place_review;
    private boolean use_service;
    private LocalDateTime registation_date;
    private LocalDateTime last_enter;

}
