package com.trymad.lootmarket.repository.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trymad.lootmarket.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    
}
