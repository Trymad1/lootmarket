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
                .placeReview(true)
                .placeService(true)
                .useService(true)
                .registationDate(LocalDateTime.now().minusDays(10))
                .lastEnter(LocalDateTime.now().minusHours(5))
                .lastUpdate(LocalDateTime.now())
                .build());
        save(User.builder()
                .id(UUID.randomUUID())
                .name("Kalok")
                .mail("kalak@mail.ru")
                .password("jahan")
                .placeReview(true)
                .placeService(true)
                .useService(false)
                .registationDate(LocalDateTime.now().minusDays(40))
                .lastEnter(LocalDateTime.now().minusHours(2))
                .lastUpdate(LocalDateTime.now())
                .build());
        save(User.builder()
                .id(UUID.randomUUID())
                .name("Vladick")
                .mail("vladed@gmail.com")
                .password("elvlado")
                .placeReview(false)
                .placeService(true)
                .useService(true)
                .registationDate(LocalDateTime.now().minusDays(100))
                .lastEnter(LocalDateTime.now().minusHours(17))
                .lastUpdate(LocalDateTime.now())
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
