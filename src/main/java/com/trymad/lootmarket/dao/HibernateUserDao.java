package com.trymad.lootmarket.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.trymad.lootmarket.model.User;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
@Primary
public class HibernateUserDao implements UserDao {

    private final EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        return Optional.ofNullable(entityManager.find(User.class, uuid));
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        entityManager.getReference(User.class, user.getId()).getId();
        entityManager.merge(user);
        return user;
    }

    @Override
    public void deleteById(UUID uuid) {
        final User user = entityManager.getReference(User.class, uuid);
        entityManager.remove(user);
    }

}
