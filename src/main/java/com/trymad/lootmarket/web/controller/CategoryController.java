package com.trymad.lootmarket.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.service.CategoryService;
import com.trymad.lootmarket.web.dto.game.category.CategoryDTO;
import com.trymad.lootmarket.web.dto.mapper.CategoryMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("games/{gameId}/categories")
public class CategoryController {

    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAll(@RequestParam Long gameId) {
        return categoryMapper.toDto(categoryService.getAll(gameId));
    }

}
