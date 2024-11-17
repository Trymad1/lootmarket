package com.trymad.lootmarket.repository.deal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trymad.lootmarket.model.Deal;

public interface DealRepository extends JpaRepository<Deal, Long> {

    @Query("SELECT d FROM Deal d JOIN FETCH service JOIN FETCH buyer JOIN FETCH paymentSystem JOIN FETCH dealStatus")
    List<Deal> fetchFindAll();

    @Query("SELECT d FROM Deal d JOIN FETCH service JOIN FETCH buyer JOIN FETCH paymentSystem JOIN FETCH dealStatus WHERE d.id = :id")
    Optional<Deal> fetchFindByid(@Param("id") Long id);

}
