package com.trymad.lootmarket.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trymad.lootmarket.model.User;

public interface SpringJpaUserDao extends JpaRepository<User, UUID> {
    
}
