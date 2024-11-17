package com.trymad.lootmarket.web.dto.game.category;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.lootmarket.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "gameId", source = "game.id")
    CategoryDTO toDto(Category category);

    @Mapping(target = "game", ignore = true)
    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryCreateDTO createDto);

    List<CategoryDTO> toDto(List<Category> category);

    @Mapping(target = "game", ignore = true)
    Category toEntity(CategoryDTO categoryDto);

    List<Category> toEntity(List<CategoryDTO> category);

}
