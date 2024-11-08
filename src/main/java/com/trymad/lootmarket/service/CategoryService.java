package com.trymad.lootmarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trymad.lootmarket.model.Category;
import com.trymad.lootmarket.repository.game.category.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public List<Category> getAll(Long gameId) {
        return categoryRepository.findByGameId(gameId);
    }

}
