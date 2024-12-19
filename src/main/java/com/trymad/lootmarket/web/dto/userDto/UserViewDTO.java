package com.trymad.lootmarket.web.dto.userDto;

import java.time.LocalDateTime;
import java.util.Set;

import com.trymad.lootmarket.model.Role;

public record UserViewDTO(
                String id,
                String name,
                String mail,
                LocalDateTime registrationDate,
                LocalDateTime lastEnter,
                boolean banned,
                Set<Role> roles,
                LocalDateTime lastUpdate) {
}
