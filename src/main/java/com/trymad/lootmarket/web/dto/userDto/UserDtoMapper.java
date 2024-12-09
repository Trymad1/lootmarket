package com.trymad.lootmarket.web.dto.userDto;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.lootmarket.model.Role;
import com.trymad.lootmarket.model.RoleEntity;
import com.trymad.lootmarket.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserViewDTO toUserViewDto(User user);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "mail", source = "mail")
    @Mapping(target = "banned", source = "banned")
    User toEntity(UserUpdateDTO userUpdateDTO);

    List<UserViewDTO> toUserViewDtoList(List<User> user);

    UserCreateDTO toUserCreateDto(User user);

    List<UserViewDTO> toUserCreateDtoList(List<User> user);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "mail", source = "mail")
    @Mapping(target = "password", source = "password")
    User toEntity(UserCreateDTO dto);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "banned", source = "banned")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "mail", source = "mail")
    @Mapping(target = "registrationDate", source = "registrationDate")
    @Mapping(target = "lastEnter", source = "lastEnter")
    @Mapping(target = "lastUpdate", source = "lastUpdate")
    User toEntityUserView(UserViewDTO dto);

    List<User> toEntityUserCreate(List<UserCreateDTO> user);

    List<User> toEntityUserView(List<UserViewDTO> user);

    default Set<Role> toRole(Set<RoleEntity> roleEntity) {
        return roleEntity.stream()
                .map(RoleEntity::getName)
                .map(name -> Role.valueOf(name.toString()))
                .collect(Collectors.toSet());
    }
}
