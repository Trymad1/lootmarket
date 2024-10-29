package com.trymad.lootmarket.web.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.lootmarket.model.User;
import com.trymad.lootmarket.web.dto.UserCreateDTO;
import com.trymad.lootmarket.web.dto.UserViewDTO;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserViewDTO toUserViewDto(User user);

    List<UserViewDTO> toUserViewDtoList(List<User> user);

    UserCreateDTO toUserCreateDto(User user);

    List<UserViewDTO> toUserCreateDtoList(List<User> user);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "mail", source = "mail")
    @Mapping(target = "password", source = "password")
    User toEntityUserCreate(UserCreateDTO dto);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "mail", source = "mail")
    @Mapping(target = "registationDate", source = "registationDate")
    @Mapping(target = "lastEnter", source = "lastEnter")
    @Mapping(target = "lastUpdate", source = "lastUpdate")
    User toEntityUserView(UserViewDTO dto);

    List<User> toEntityUserCreate(List<UserCreateDTO> user);

    List<User> toEntityUserView(List<UserViewDTO> user);

}
