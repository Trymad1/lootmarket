package com.trymad.lootmarket.repository.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trymad.lootmarket.model.User;

public interface SpringJpaUserRepository extends JpaRepository<User, UUID> {
    
}
