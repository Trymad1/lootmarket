package com.trymad.lootmarket.repository.deal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trymad.lootmarket.model.Deal;

public interface DealRepository extends JpaRepository<Deal, Long> {

    @Query("SELECT d FROM Deal d JOIN FETCH service JOIN FETCH buyer JOIN FETCH buyer.roles JOIN FETCH paymentSystem JOIN FETCH dealStatus")
    List<Deal> fetchFindAll();

    @Query("SELECT d FROM Deal d JOIN FETCH service JOIN FETCH buyer JOIN FETCH buyer.roles JOIN FETCH paymentSystem JOIN FETCH dealStatus WHERE d.id = :id")
    Optional<Deal> fetchFindByid(@Param("id") Long id);

    @Query("SELECT d FROM Deal d JOIN FETCH service JOIN FETCH buyer JOIN FETCH buyer.roles JOIN FETCH paymentSystem JOIN FETCH dealStatus WHERE d.service.id = :serviceId")
    List<Deal> fetchFindByServiceId(@Param("serviceId") Long serviceId);

    @Query("""
        SELECT d FROM Deal d 
        JOIN FETCH service 
        JOIN FETCH service.category
        JOIN FETCH service.author
        JOIN FETCH service.author.roles
        JOIN FETCH buyer 
        JOIN FETCH buyer.roles 
        JOIN FETCH paymentSystem 
        JOIN FETCH dealStatus 
        WHERE d.dealEnd IS NOT NULL AND d.dealEnd BETWEEN :from AND :to
            
            """)
    List<Deal> fetchFindByDate(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
