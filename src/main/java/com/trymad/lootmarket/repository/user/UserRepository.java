package com.trymad.lootmarket.repository.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.trymad.lootmarket.model.User;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(UUID uuid);

    User save(User user);

    void deleteById(UUID uuid);

}
