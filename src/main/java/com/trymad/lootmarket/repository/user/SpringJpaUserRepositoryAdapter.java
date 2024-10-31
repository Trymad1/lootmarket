package com.trymad.lootmarket.repository.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.trymad.lootmarket.model.User;

import lombok.RequiredArgsConstructor;

@Repository
@Primary
@RequiredArgsConstructor
public class SpringJpaUserRepositoryAdapter implements UserRepository {

    private final SpringJpaUserRepository jpaDao;

    @Override
    public List<User> findAll() {
        return jpaDao.findAll();
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        return jpaDao.findById(uuid);
    }

    @Override
    public User save(User user) {
        return jpaDao.save(user);
    }

    @Override
    public void deleteById(UUID uuid) {
        jpaDao.deleteById(uuid);
    }


}
