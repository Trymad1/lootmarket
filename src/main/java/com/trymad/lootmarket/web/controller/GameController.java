package com.trymad.lootmarket.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.service.GameService;
import com.trymad.lootmarket.web.dto.game.GameDTO;
import com.trymad.lootmarket.web.dto.mapper.GameDtoMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final GameDtoMapper gameDtoMapper;

    @GetMapping("")
    public List<GameDTO> getAll() {
        return gameDtoMapper.toDto(gameService.getAll());
    }
    
}
