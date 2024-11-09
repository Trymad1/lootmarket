package com.trymad.lootmarket.repository.game.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trymad.lootmarket.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Modifying
    @Query("DELETE FROM Category c WHERE c.game.id = :gameId")
    void deleteAllCategoriesByGameId(@Param("gameId") Long gameId);

    @Query("SELECT c FROM Category c JOIN FETCH c.game g WHERE g.id = :gameId")
    List<Category> getCategoriesByGameId(@Param("gameId") Long gameId);

}
