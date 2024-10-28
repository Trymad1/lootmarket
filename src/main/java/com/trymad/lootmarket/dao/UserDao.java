package com.trymad.lootmarket.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.trymad.lootmarket.model.User;

public interface UserDao {

    List<User> findAll();

    Optional<User> findById(UUID uuid);

    User save(User user);

    void deleteById(UUID uuid);

}
