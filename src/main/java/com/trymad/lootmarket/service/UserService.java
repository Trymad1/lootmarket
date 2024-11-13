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
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User get(UUID uuid) {
        log.debug("Get user, uuid: {}", uuid);

        if (uuid == null)
            throw notFoundExceptionById(uuid);
        return userRepository.findById(uuid).orElseThrow(
                () -> notFoundExceptionById(uuid));
    }

    private EntityNotFoundException notFoundExceptionById(UUID uuid) {
        log.debug("Throw entityNotFoundException");

        return new EntityNotFoundException("User with id " + uuid.toString() + " not found");
    }

    public boolean existsById(UUID uuid) {
        log.debug("Check user exists, uuid: {}", uuid);

        return userRepository.existsById(uuid);
    }

    public boolean existsByIdOrThrow(UUID uuid) {
        log.debug("Check user exists or throw, uuid: {}", uuid);

        final boolean exists = uuid == null ? false : userRepository.existsById(uuid);

        if (!exists) {
            throw notFoundExceptionById(uuid);
        }

        return exists;
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        log.debug("Get all users");

        return userRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        log.debug("Save user, uuid: {}", user.getId());

        final LocalDateTime now = LocalDateTime.now();

        user.setId(UUID.randomUUID());
        user.setRegistrationDate(now);
        user.setLastEnter(now);
        user.setLastUpdate(now);

        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        log.debug("Saving user, uuid: {}", user.getId());

        this.get(user.getId());
        user.setLastUpdate(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Transactional
    public void delete(UUID uuid) {
        log.debug("Delete user, uuid: {}", uuid);

        userRepository.deleteById(uuid);
    }

    @Transactional(readOnly = true)
    public void enrichUserData(User user) {
        log.debug("Enrich user, uuid: {}", user.getId());

        final User inBaseUser = this.get(user.getId());
        user.setLastEnter(inBaseUser.getLastEnter());
        user.setLastUpdate(inBaseUser.getLastUpdate());
        user.setRegistrationDate(inBaseUser.getRegistrationDate());
    }
}
