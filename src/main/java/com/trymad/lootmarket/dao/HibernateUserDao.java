package com.trymad.lootmarket.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

        List<User> allUsers = null;
        try (Session session = entityManager.unwrap(Session.class)) {

            Query<User> query = session.createQuery("FROM User", User.class);
            allUsers = query.list();

        }

        return allUsers;
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        User user = null;

        try (Session session = entityManager.unwrap(Session.class)) {
            user = session.get(User.class, uuid);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public User save(User user) {

        try (Session session = entityManager.unwrap(Session.class)) {
            session.persist(user);
        }

        return user;
    }

    @Override
    public User update(User user) {
        try (Session session = entityManager.unwrap(Session.class)) {
            session.getReference(User.class, user.getId()).getId();
            session.merge(user);
        }

        return user;
    }

    @Override
    public void deleteById(UUID uuid) {
        try (Session session = entityManager.unwrap(Session.class)) {
            User user = session.getReference(User.class, uuid);
            session.remove(user);
        }
    }

}
