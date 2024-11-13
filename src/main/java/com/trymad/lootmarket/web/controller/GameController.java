package com.trymad.lootmarket.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.model.Game;
import com.trymad.lootmarket.service.GameService;
import com.trymad.lootmarket.web.dto.game.GameCreateDTO;
import com.trymad.lootmarket.web.dto.game.GameDTO;
import com.trymad.lootmarket.web.dto.game.GameDtoMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("{id}")
    public GameDTO getById(@PathVariable Long id) {
        return gameDtoMapper.toDto(gameService.get(id));
    }

    @PostMapping
    public GameDTO createGame(@RequestBody GameCreateDTO createDto) {
        final Game game = gameDtoMapper.toEntity(createDto);

        return gameDtoMapper.toDto(gameService.save(game));
    }

    @PutMapping("{id}")
    public GameDTO updateGame(@RequestBody GameCreateDTO updateDto, @PathVariable Long id) {
        final Game game = gameDtoMapper.toEntity(updateDto);
        game.setId(id);

        return gameDtoMapper.toDto(gameService.update(game));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.delete(id);

        return ResponseEntity.ok().build();
    }

}
