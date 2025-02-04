package com.trymad.lootmarket.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.model.Category;
import com.trymad.lootmarket.service.GameService;
import com.trymad.lootmarket.web.dto.game.category.CategoryCreateDTO;
import com.trymad.lootmarket.web.dto.game.category.CategoryDTO;
import com.trymad.lootmarket.web.dto.game.category.CategoryMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("api/games/{gameId}/categories")
public class CategoryController {

    private final CategoryMapper categoryMapper;
    private final GameService gameService;

    @GetMapping
    public List<CategoryDTO> getCategoriesByGameId(@PathVariable Long gameId) {
        return categoryMapper.toDto(gameService.getGameCategories(gameId));
    }

    @PostMapping
    public CategoryDTO addCategoryForGame(@PathVariable Long gameId, @RequestBody CategoryCreateDTO createDto) {
        final Category category = categoryMapper.toEntity(createDto);
        return categoryMapper.toDto(gameService.saveCategory(gameId, category));
    }

    @PutMapping("/{categoryId}")
    public CategoryDTO changeCategory(@PathVariable Long gameId, @PathVariable Long categoryId,
            @RequestBody CategoryCreateDTO updateDto) {
        final Category category = categoryMapper.toEntity(updateDto);
        category.setId(categoryId);
        return categoryMapper.toDto(gameService.updateCategory(gameId, category));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long gameId, @PathVariable Long categoryId) {
        gameService.deleteCategory(gameId, categoryId);
        return ResponseEntity.ok().build();
    }

}
