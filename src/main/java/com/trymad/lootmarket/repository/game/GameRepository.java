package com.trymad.lootmarket.repository.game;


import org.springframework.data.jpa.repository.JpaRepository;

import com.trymad.lootmarket.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
    
}
