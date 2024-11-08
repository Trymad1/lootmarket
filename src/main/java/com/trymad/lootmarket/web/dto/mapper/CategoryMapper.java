package com.trymad.lootmarket.web.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.trymad.lootmarket.model.Category;
import com.trymad.lootmarket.web.dto.game.service.CategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(Category category);

    List<CategoryDTO> toDto(List<Category> category);

    Category toEntity(CategoryDTO categoryDTO);

    List<Category> toEntity(List<CategoryDTO> category);
}
