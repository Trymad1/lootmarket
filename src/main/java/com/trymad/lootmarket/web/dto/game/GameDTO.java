package com.trymad.lootmarket.web.dto.game;

import java.util.List;

import com.trymad.lootmarket.model.ServiceCategory;

public record GameDTO(Long id, String name, List<ServiceCategory> serviceCategories) {
    
}
