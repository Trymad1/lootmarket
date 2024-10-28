package com.trymad.lootmarket.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.trymad.lootmarket.model.User;

@Repository
public class DummyUserDaoImpl implements UserDao {

    Map<UUID, User> db = new HashMap<>();

    DummyUserDaoImpl() {
        initStartDb();
    }

    private void initStartDb() {
        save(User.builder()
                .id(UUID.randomUUID())
                .name("Oleg")
                .mail("olegbobrik@urk.ua")
                .password("exsensesium")
                .place_review(true)
                .place_service(true)
                .use_service(true)
                .registation_date(LocalDateTime.now().minusDays(10))
                .last_enter(LocalDateTime.now().minusHours(5))
                .build());
        save(User.builder()
                .id(UUID.randomUUID())
                .name("Kalok")
                .mail("kalak@mail.ru")
                .password("jahan")
                .place_review(true)
                .place_service(true)
                .use_service(false)
                .registation_date(LocalDateTime.now().minusDays(40))
                .last_enter(LocalDateTime.now().minusHours(2))
                .build());
        save(User.builder()
                .id(UUID.randomUUID())
                .name("Vladick")
                .mail("vladed@gmail.com")
                .password("elvlado")
                .place_review(false)
                .place_service(true)
                .use_service(true)
                .registation_date(LocalDateTime.now().minusDays(100))
                .last_enter(LocalDateTime.now().minusHours(17))
                .build());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(db.values());
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        return Optional.ofNullable(db.get(uuid));
    }

    @Override
    public User save(User user) {
        db.put(user.getId(), user);
        return user;
    }

    @Override
    public void deleteById(UUID uuid) {
        db.remove(uuid);
    }

}
