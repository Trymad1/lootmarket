package com.trymad.lootmarket.repository.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trymad.lootmarket.model.User;
import com.trymad.lootmarket.web.dto.user.UserStatsDTO;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :userId")
    Optional<User> findById(@Param("userId") UUID id);

    @Query("SELECT u FROM User u JOIN FETCH u.roles")
    List<User> findAll();

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.mail = :mail")
    Optional<User> findByMail(@Param("mail") String mail);
    @Query(value = "SELECT new com.trymad.lootmarket.web.dto.user.UserStatsDTO(" +
                   "(SELECT COUNT(ua) FROM UserAd ua WHERE ua.user.id = :userId), " +  // Заменили Service на UserAd
                   "(SELECT COUNT(d) FROM Deal d WHERE d.userAd.user.id = :userId), " + // Заменили Service на UserAd
                   "(SELECT COUNT(d) FROM Deal d WHERE d.user_buyer.id = :userId), " +
                   "(SELECT up.name FROM UserPayment up JOIN up.paymentSystem p WHERE up.user.id = :userId GROUP BY up.name), " + // Заменили PaymentDetail на UserPayment
                   "(SELECT ua.time FROM users_activity ua WHERE ua.user_id = :userId), " +  // Заменили user_activity на users_activity
                   "(SELECT d.deal_start FROM Deal d WHERE d.userAd.user.id = :userId), " +  // Заменили Service на UserAd
                   "(SELECT d.deal_start FROM Deal d WHERE d.user_buyer.id = :userId)) " +
                   "FROM User u WHERE u.id = :userId", nativeQuery = true) // Используем нативный SQL
    UserStatsDTO getUserStats(@Param("userId") UUID userId);
}
