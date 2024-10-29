package com.trymad.lootmarket.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.trymad.lootmarket.dao.UserDao;
import com.trymad.lootmarket.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public User getUser(UUID uuid) {
        return userDao.findById(uuid).orElseThrow(
                () -> new RuntimeException("User with id " + uuid.toString() + " not found"));
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public void deleteUser(UUID uuid) {
        userDao.deleteById(uuid);
    }

    public void enrichUserData(User user) {
        final User inBaseUser = this.getUser(user.getId());
        user.setLastEnter(inBaseUser.getLastEnter());
        user.setLastUpdate(inBaseUser.getLastUpdate());
        user.setRegistationDate(inBaseUser.getRegistationDate());
    }

}
