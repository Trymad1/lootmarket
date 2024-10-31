package com.trymad.lootmarket.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.repository.game.GameRepository;
import com.trymad.lootmarket.model.Game;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public Game get(Long id) {
        return gameRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Game with id " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    @Transactional
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Transactional
    public Game update(Game game) {
        this.get(game.getId());
        return gameRepository.save(game);
    }

    @Transactional
    public void delete(Long id) {
        gameRepository.deleteById(id);
    }

}
