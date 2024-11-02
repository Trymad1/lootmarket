package com.trymad.lootmarket.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.repository.game.GameRepository;
import com.trymad.lootmarket.model.Game;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public Game get(Long id) {
        log.debug("Get game, id: {}", id);
        return gameRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Game with id " + id + " not found"));
    }

    public Game get(String name) {
        log.debug("Get game, name: {}", name);
        return gameRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("Game with name " + name + " not found"));
    }

    @Transactional(readOnly = true)
    public List<Game> getAll() {
        log.debug("Get all games");
        return gameRepository.findAll();
    }

    @Transactional
    public Game save(Game game) {
        log.debug("Save game, id: {}", game.getId());
        return gameRepository.save(game);
    }

    @Transactional
    public Game update(Game game) {
        log.debug("Update game, id: {}", game.getId());
        log.debug("Check that game with id {} is present", game.getId());
        final Game oldGame = this.get(game.getId());
        game.setServiceCategories(oldGame.getServiceCategories());
        log.debug("It present, update");
        return gameRepository.save(game);
    }

    @Transactional
    public void delete(Long id) {
        log.debug("Delete game, id: {}", id);
        gameRepository.deleteById(id);
    }

}