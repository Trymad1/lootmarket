package com.trymad.lootmarket.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.web.dto.game.service.CategoryDTO;
import com.trymad.lootmarket.web.dto.mapper.CategoryMapper;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("games/{gameId}/categories")
public class ServiceCategoryController {

    private CategoryMapper serviceCategoryMapper;

    // @GetMapping
    // public List<ServiceCategoryDTO> getAll(@RequestParam Long gameId) {

    // }

}
