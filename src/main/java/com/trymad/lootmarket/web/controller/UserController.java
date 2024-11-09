package com.trymad.lootmarket.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.model.User;
import com.trymad.lootmarket.service.UserService;
import com.trymad.lootmarket.web.dto.mapper.UserDtoMapper;
import com.trymad.lootmarket.web.dto.user.UserCreateDTO;
import com.trymad.lootmarket.web.dto.user.UserViewDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @GetMapping()
    public List<UserViewDTO> getAllUsers() {
        return userDtoMapper.toUserViewDtoList(userService.getAll());
    }

    @GetMapping("{id}")
    public UserViewDTO getUserById(@PathVariable UUID id) {
        return userDtoMapper.toUserViewDto(userService.get(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userService.delete(id);
        
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public UserViewDTO createUser(@RequestBody UserCreateDTO userCreateDTO) {
        final User user = userDtoMapper.toEntityUserCreate(userCreateDTO);

        return userDtoMapper.toUserViewDto(userService.save(user));
    }

    @PutMapping("{id}")
    public UserViewDTO updateUser(@RequestBody UserCreateDTO userCreateDto,
            @PathVariable UUID id) {
        final User user = userDtoMapper.toEntityUserCreate(userCreateDto);
        user.setId(id);
        userService.enrichUserData(user);

        return userDtoMapper.toUserViewDto(userService.update(user));
    }

}
