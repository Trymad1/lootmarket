package com.trymad.lootmarket.web.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.trymad.lootmarket.model.Game;
import com.trymad.lootmarket.web.dto.game.GameDTO;

@Mapper(componentModel = "spring")
public interface GameDtoMapper {

    GameDTO toDto(Game game);

    Game toEntity(GameDTO gameDto);

    List<GameDTO> toDto(List<Game> games);

    List<Game> toEntities(List<GameDTO> gamesDTo);

}
