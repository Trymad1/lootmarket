package com.trymad.lootmarket.web.dto.review;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.lootmarket.model.Review;

@Mapper(componentModel = "spring")
public interface ReviewDTOMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "dealId", source = "deal.id")
    ReviewDTO toDto(Review review);

    List<ReviewDTO> toDto(List<Review> reviews);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "deal", ignore = true)
    Review toEntity(ReviewDTO reviewDTO);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "deal", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    Review toEntity(ReviewCreateDTO reviewCreateDTO);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "deal", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    Review toEntity(ReviewUpdateDTO reviewUpdateDTO);

}
