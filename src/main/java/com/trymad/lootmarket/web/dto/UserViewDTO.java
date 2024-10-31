package com.trymad.lootmarket.web.dto;

import java.time.LocalDateTime;

public record UserViewDTO(
        String id,
        String name,
        String mail,
        LocalDateTime registrationDate,
        LocalDateTime lastEnter,
        LocalDateTime lastUpdate) { }
