package com.trymad.lootmarket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.dao.user.UserDao;
import com.trymad.lootmarket.model.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    @Transactional(readOnly = true)
    public User getUser(UUID uuid) {
        return userDao.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("User with id " + uuid.toString() + " not found"));
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Transactional
    public User saveUser(User user) {
        final LocalDateTime now = LocalDateTime.now();

        user.setId(UUID.randomUUID());
        user.setRegistrationDate(now);
        user.setLastEnter(now);
        user.setLastUpdate(now);

        return userDao.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        user.setLastUpdate(LocalDateTime.now());
        return userDao.update(user);
    }

    @Transactional
    public void deleteUser(UUID uuid) {
        userDao.deleteById(uuid);
    }

    public void enrichUserData(User user) {
        final User inBaseUser = this.getUser(user.getId());
        user.setLastEnter(inBaseUser.getLastEnter());
        user.setLastUpdate(inBaseUser.getLastUpdate());
        user.setRegistrationDate(inBaseUser.getRegistrationDate());
    }
}
