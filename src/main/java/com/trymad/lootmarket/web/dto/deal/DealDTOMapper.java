package com.trymad.lootmarket.web.dto.deal;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.lootmarket.model.Deal;

@Mapper(componentModel = "spring")
public interface DealDTOMapper {

    @Mapping(target = "serviceId", source = "service.id")
    @Mapping(target = "buyerId", source = "buyer.id")
    @Mapping(target = "paymentSystemId", source = "paymentSystem.id")
    @Mapping(target = "dealStatus", source = "dealStatus.name")
    DealDTO toDto(Deal deal);

    List<DealDTO> toDto(List<Deal> deals);

    @Mapping(target = "service", ignore = true)
    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "paymentSystem", ignore = true)
    @Mapping(target = "dealStatus", ignore = true)
    Deal toEntity(DealDTO dealDto);

    @Mapping(target = "service", ignore = true)
    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "paymentSystem", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dealStart", ignore = true)
    @Mapping(target = "dealStatus", ignore = true)
    Deal toEntity(DealUpdateDTO dealUpdateDTO);

    @Mapping(target = "service", ignore = true)
    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "paymentSystem", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dealStart", ignore = true)
    @Mapping(target = "dealEnd", ignore = true)
    @Mapping(target = "dealStatus", ignore = true)
    Deal toEntity(DealCreateDTO dealCreateDTO);

}
