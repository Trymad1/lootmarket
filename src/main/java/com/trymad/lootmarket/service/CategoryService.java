package com.trymad.lootmarket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trymad.lootmarket.model.Category;
import com.trymad.lootmarket.model.Game;
import com.trymad.lootmarket.repository.game.category.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll(Game game) {
        return game.getCategories();
    }

    @Transactional
    public Category save(Game game, Category category) {
        category.setGame(game);
        game.getCategories().add(category);

        return categoryRepository.save(category);
    }

    @Transactional
    public void delete(Game game, Long categoryId) {
        Category category = game.getCategories().stream().filter(service -> service.getId() == categoryId)
                .findFirst().get();
        game.getCategories().remove(category);
    }

}
