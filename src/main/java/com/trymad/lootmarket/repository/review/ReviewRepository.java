package com.trymad.lootmarket.repository.review;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trymad.lootmarket.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r JOIN FETCH r.deal JOIN FETCH r.user")
    List<Review> fetchFindAll();

    @Query("SELECT r FROM Review r JOIN FETCH r.deal JOIN FETCH r.user JOIN FETCH r.user.roles WHERE r.deal.id = :id")
    List<Review> fetchFindAllByDealId(@Param("id") Long dealId);

    @Query("SELECT r FROM Review r JOIN FETCH r.deal JOIN FETCH r.user WHERE r.id = :id")
    Optional<Review> fetchGetById(@Param("id") Long id);

    @Query("SELECT AVG(r.grade) FROM Review r WHERE r.deal.id = :dealId")
    Double findAverageGradeByDealId(@Param("dealId") Long dealId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.deal.id = :dealId")
    Long countReviewsByDealId(@Param("dealId") Long dealId);
}
