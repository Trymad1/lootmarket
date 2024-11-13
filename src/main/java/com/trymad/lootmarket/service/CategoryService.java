package com.trymad.lootmarket.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.model.Category;
import com.trymad.lootmarket.model.Game;
import com.trymad.lootmarket.repository.game.category.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public List<Category> getAll(Long gameId) {
        return categoryRepository.getCategoriesByGameId(gameId);
    }

    @Transactional(readOnly = true)
    public Category get(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category with id " + id + " not found"));
    }

    @Transactional
    public Category save(Game game, Category category) {
        category.setGame(game);
        game.getCategories().add(category);

        return categoryRepository.save(category);
    }

    @Transactional
    public void delete(Long gameId, Long categoryId) {
        final Category category = this.get(categoryId);

        checkValidGameId(category.getGame().getId(), gameId);

        categoryRepository.deleteById(categoryId);
    }

    @Transactional
    public Category update(Category category, Long gameId) {
        final Category dbCategory = this.get(category.getId());
        final Game game = dbCategory.getGame();

        checkValidGameId(game.getId(), gameId);

        category.setGame(dbCategory.getGame());

        return categoryRepository.save(category);
    }

    @Transactional
    public void cascadeDelete(Game game) {
        game.getCategories().clear();
        categoryRepository.deleteAllCategoriesByGameId(game.getId());
    }

    private boolean checkValidGameId(Long except, Long given) {
        if (except != given) {
            throw new EntityNotFoundException("Game with ID " + given + " not found for the given category");
        }

        return true;
    }

}
