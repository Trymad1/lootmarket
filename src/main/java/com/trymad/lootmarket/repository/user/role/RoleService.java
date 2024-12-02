package com.trymad.lootmarket.repository.user.role;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.model.Role;
import com.trymad.lootmarket.model.RoleEntity;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public RoleEntity get(Role role) {
        return roleRepository.findByName(role).orElseThrow(
            () -> new EntityNotFoundException("Role " + role.name() + " not found"));
    }
}
