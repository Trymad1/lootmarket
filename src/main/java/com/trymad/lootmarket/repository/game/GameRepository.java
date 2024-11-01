package com.trymad.lootmarket.repository.game;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trymad.lootmarket.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g LEFT JOIN FETCH g.serviceCategories")
    List<Game> findAll();
    
    @Query("SELECT g FROM Game g LEFt JOIN FETCH g.serviceCategories WHERE g.id = :id")
    Optional<Game> findById(@Param("id") Long id);

    @Query("SELECT g FROM Game g LEFT JOIN FETCH g.serviceCategories WHERE g.name = :name")
    Optional<Game> findByName(@Param("name") String name);

}
