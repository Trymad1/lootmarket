package com.trymad.lootmarket.web.dto.mapper;

import org.mapstruct.Mapper;

import com.trymad.lootmarket.model.ServiceCategory;
import com.trymad.lootmarket.web.dto.game.service.ServiceCategoryDTO;

@Mapper(componentModel = "spring")
public interface ServiceCategoryMapper {

    ServiceCategoryDTO toDto(ServiceCategory category);

    ServiceCategory toEntity(ServiceCategoryDTO categoryDTO);
}
