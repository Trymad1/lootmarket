package com.trymad.lootmarket.web.dto.game;

import java.util.List;

import com.trymad.lootmarket.web.dto.game.service.CategoryDTO;

public record GameDTO(Long id, String name, List<CategoryDTO> categories) {

}
