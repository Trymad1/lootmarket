package com.trymad.lootmarket.repository.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trymad.lootmarket.model.PaymentSystem;
import com.trymad.lootmarket.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :userId")
    Optional<User> findById(@Param("userId") UUID id);

    @Query("SELECT u FROM User u JOIN FETCH u.roles")
    List<User> findAll();

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.mail = :mail")
    Optional<User> findByMail(@Param("mail") String mail);

    @Query("SELECT COUNT(s.id) FROM UserAd s WHERE s.author.id = :userId")
    Long countTotalServicesPosted(@Param("userId") UUID userId);

    @Query("SELECT COUNT(d.id) FROM Deal d WHERE d.buyer.id = :userId")
    Long countTotalServicesPurchased(@Param("userId") UUID userId);

    @Query("SELECT COUNT(d.id) FROM Deal d JOIN d.service s WHERE s.author.id = :userId")
    Long countTotalServicesSold(@Param("userId") UUID userId);

    @Query("SELECT d.dealStart FROM Deal d JOIN d.service s WHERE s.author.id = :userId")
    List<LocalDateTime> findServiceSaleDates(@Param("userId") UUID userId);

    @Query("SELECT d.dealStart FROM Deal d WHERE d.buyer.id = :userId")
    List<LocalDateTime> findServicePurchaseDates(@Param("userId") UUID userId);

    @Query("SELECT ua.time FROM UserActivity ua WHERE ua.userId = :userId")
    List<LocalDateTime> findActivityDates(@Param("userId") UUID userId);

    @Query("SELECT DISTINCT up.paymentSystem FROM UserPayment up JOIN up.paymentSystem WHERE up.user.id = :userId")
    List<PaymentSystem> findPaymentSystems(@Param("userId") UUID userId);

    @Query("SELECT ua.time FROM UserActivity ua WHERE ua.time BETWEEN :from AND :to")
    List<LocalDateTime> findActivityDatesByDate(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query("SELECT u.registrationDate FROM User u WHERE u.registrationDate BETWEEN :from AND :to")
    List<LocalDateTime> findRegistrationTimesByDate(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
