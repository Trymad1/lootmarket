package com.trymad.lootmarket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.repository.user.UserRepository;
import com.trymad.lootmarket.model.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User get(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("User with id " + uuid.toString() + " not found"));
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        final LocalDateTime now = LocalDateTime.now();

        user.setId(UUID.randomUUID());
        user.setRegistrationDate(now);
        user.setLastEnter(now);
        user.setLastUpdate(now);

        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        this.get(user.getId());
        user.setLastUpdate(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Transactional
    public void delete(UUID uuid) {
        userRepository.deleteById(uuid);
    }

    @Transactional(readOnly = true)
    public void enrichUserData(User user) {
        final User inBaseUser = this.get(user.getId());
        user.setLastEnter(inBaseUser.getLastEnter());
        user.setLastUpdate(inBaseUser.getLastUpdate());
        user.setRegistrationDate(inBaseUser.getRegistrationDate());
    }
}
