package com.trymad.lootmarket.repository.user.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trymad.lootmarket.model.Role;
import com.trymad.lootmarket.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    
    Optional<RoleEntity> findByName(Role role);

}
