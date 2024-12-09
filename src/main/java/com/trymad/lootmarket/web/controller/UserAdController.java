package com.trymad.lootmarket.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.model.UserAd;
import com.trymad.lootmarket.service.ReviewService;
import com.trymad.lootmarket.service.UserAdService;
import com.trymad.lootmarket.web.dto.userAd.UserAdCreateDTO;
import com.trymad.lootmarket.web.dto.userAd.UserAdDTO;
import com.trymad.lootmarket.web.dto.userAd.UserAdDTOMapper;
import com.trymad.lootmarket.web.dto.userAd.UserAdUpdateDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor

@RestController
@RequestMapping("api/services")
public class UserAdController {

    private final UserAdService userAdService;
    private final UserAdDTOMapper userAdDTOMapper;

    @GetMapping
    public List<UserAdDTO> getAll() {
        List<UserAdDTO> dto = userAdDTOMapper.toDto(userAdService.getAll());

        return userAdService.enrich(dto);
    }

    @GetMapping("{id}")
    public UserAdDTO getById(@PathVariable Long id) {
        return userAdDTOMapper.toDto(userAdService.getById(id));
    }

    @PostMapping
    public UserAdDTO create(@RequestBody UserAdCreateDTO userAdCreateDTO) {
        return userAdDTOMapper.toDto(userAdService.create(userAdCreateDTO));
    }

    @PutMapping("{id}")
    public UserAdDTO update(@PathVariable Long id, @RequestBody UserAdUpdateDTO userApUpdateDTO) {
        final UserAd userAd = userAdDTOMapper.toEntity(userApUpdateDTO);
        userAd.setId(id);

        return userAdDTOMapper.toDto(userAdService.update(userAd));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userAdService.deleteById(id);
    }

}
