package com.trymad.lootmarket.web.dto.userAd;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.lootmarket.model.UserAd;

@Mapper(componentModel = "spring")
public interface UserAdDTOMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "authorName", source = "author.name")
    UserAdDTO toDto(UserAd userAd);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "deals", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    UserAd toEntity(UserAdCreateDTO userAdCreateDTO);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "deals", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    UserAd toEntity(UserAdUpdateDTO userAdUpdateDTO);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "deals", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    UserAd toEntity(UserAdDTO userAdDTO);

    List<UserAdDTO> toDto(List<UserAd> userAds);
}
