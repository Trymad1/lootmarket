package com.trymad.lootmarket.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.repository.game.GameRepository;
import com.trymad.lootmarket.model.Category;
import com.trymad.lootmarket.model.Game;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final CategoryService categoryService;

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

        final Game oldGame = this.get(game.getId());
        game.setCategories(oldGame.getCategories());

        return gameRepository.save(game);
    }

    @Transactional
    public void delete(Long id) {
        log.debug("Delete game, id: {}", id);

        categoryService.cascadeDelete(this.get(id));
        gameRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Category> getGameCategories(Long gameId) {
        log.debug("Get game categories, gameId: {}", gameId);

        return categoryService.getAll(gameId);
    }

    @Transactional
    public Category saveCategory(Long gameId, Category category) {
        log.debug("Save category, gameId: {}, categoryId: {}", gameId, category.getId());

        final Game game = this.get(gameId);
        return categoryService.save(game, category);
    }

    public Category updateCategory(Long gameId, Category category) {
        log.debug("Update category, gameId: {}, categoryId: {}", gameId, category.getId());

        return categoryService.update(category, gameId);
    }

    @Transactional
    public void deleteCategory(Long gameId, Long categoryId) {
        log.debug("Delete category, gameId: {}, categoryId: {}", gameId, categoryId);
        categoryService.delete(gameId, categoryId);
    }

}
