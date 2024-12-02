package com.trymad.lootmarket.web.dto.user;

import java.time.LocalDateTime;
import java.util.Set;

import com.trymad.lootmarket.model.Role;

public record UserViewDTO(
                String id,
                String name,
                String mail,
                LocalDateTime registrationDate,
                LocalDateTime lastEnter,
                Set<Role> roles,
                LocalDateTime lastUpdate) {
}
