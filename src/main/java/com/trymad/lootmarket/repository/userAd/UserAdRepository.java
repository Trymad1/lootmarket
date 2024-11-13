package com.trymad.lootmarket.repository.userAd;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trymad.lootmarket.model.UserAd;

public interface UserAdRepository extends JpaRepository<UserAd, Long> {

    @Query("SELECT ua FROM UserAd JOIN FETCH ua.author JOIN FETCH ua.category WHERE ua.id = :id")
    Optional<UserAd> fetchFindById(@Param("id") Long id);

}
