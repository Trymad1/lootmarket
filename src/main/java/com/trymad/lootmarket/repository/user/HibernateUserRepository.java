package com.trymad.lootmarket.repository.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.trymad.lootmarket.model.User;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@Deprecated
@Repository
@AllArgsConstructor
public class HibernateUserRepository {

    private final EntityManager entityManager;

    public List<User> findAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    public Optional<User> findById(UUID uuid) {
        return Optional.ofNullable(entityManager.find(User.class, uuid));
    }

    public User save(User user) {
        return entityManager.merge(user);
    }

    public void deleteById(UUID uuid) {
        final User user = entityManager.getReference(User.class, uuid);
        entityManager.remove(user);
    }

}
