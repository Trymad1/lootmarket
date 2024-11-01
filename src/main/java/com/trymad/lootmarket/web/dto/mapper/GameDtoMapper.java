package com.trymad.lootmarket.web.dto.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.lootmarket.model.Game;
import com.trymad.lootmarket.web.dto.game.GameCreateDTO;
import com.trymad.lootmarket.web.dto.game.GameDTO;

@Mapper(componentModel = "spring")
public interface GameDtoMapper {

    GameDTO toDto(Game game);

    List<GameDTO> toDto(List<Game> games);

    Game toEntity(GameDTO gameDto);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", source = "name")
    Game toEntity(GameCreateDTO gameCreateDTO);

    List<Game> toEntity(List<GameDTO> gamesDTo);

}
