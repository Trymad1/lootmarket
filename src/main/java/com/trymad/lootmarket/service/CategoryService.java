package com.trymad.lootmarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trymad.lootmarket.model.Category;
import com.trymad.lootmarket.model.Game;
import com.trymad.lootmarket.repository.game.category.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll(Game game) {
        return game.getCategories();
    }

    public Category saveCategory(Game game, Category category) {
        category.setGame(game);
        game.getCategories().add(category);

        return categoryRepository.save(category);
    }

}
