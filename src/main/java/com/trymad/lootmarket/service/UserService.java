package com.trymad.lootmarket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.repository.user.UserRepository;
import com.trymad.lootmarket.repository.user.role.RoleService;
import com.trymad.lootmarket.web.dto.user.UserStatsDTO;
import com.trymad.lootmarket.web.dto.user.UserWithStats;
import com.trymad.lootmarket.model.MyUserDetails;
import com.trymad.lootmarket.model.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Transactional(readOnly = true)
    public User get(UUID uuid) {
        log.debug("Get user, uuid: {}", uuid);

        if (uuid == null)
            throw notFoundExceptionById(uuid);
        return userRepository.findById(uuid).orElseThrow(
                () -> notFoundExceptionById(uuid));
    }

    public User getReference(UUID uuid) {
        return userRepository.getReferenceById(uuid);
    }

    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        final User user = userRepository.findByMail(mail).orElseThrow(
            () -> new EntityNotFoundException("User with mail " + mail + " not found")
        );

        return new MyUserDetails(user);
    }

    public EntityNotFoundException notFoundExceptionById(UUID uuid) {
        log.debug("Throw entityNotFoundException");

        return new EntityNotFoundException("User with id " + uuid.toString() + " not found");
    }

    @Transactional(readOnly = true)
    public boolean existsById(UUID uuid) {
        log.debug("Check user exists, uuid: {}", uuid);

        return userRepository.existsById(uuid);
    }

    @Transactional(readOnly = true)
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

    @Transactional
    public UserStatsDTO getUserWithStats(UUID userId) {
        existsByIdOrThrow(userId);
        return userRepository.getUserStats(userId);

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
