package com.trymad.lootmarket.web.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.lootmarket.model.Category;
import com.trymad.lootmarket.web.dto.game.category.CategoryCreateDTO;
import com.trymad.lootmarket.web.dto.game.category.CategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(Category category);

    @Mapping(target = "game", ignore = true)
    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryCreateDTO createDto);

    @Mapping(target = "game", ignore = true)
    List<CategoryDTO> toDto(List<Category> category);

    @Mapping(target = "game", ignore = true)
    Category toEntity(CategoryDTO categoryDto);

    List<Category> toEntity(List<CategoryDTO> category);
}
