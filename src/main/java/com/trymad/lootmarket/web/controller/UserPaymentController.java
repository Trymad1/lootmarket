package com.trymad.lootmarket.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.service.userPayment.UserPaymentService;
import com.trymad.lootmarket.web.dto.userPayment.UserPaymentCreateDTO;
import com.trymad.lootmarket.web.dto.userPayment.UserPaymentDTO;
import com.trymad.lootmarket.web.dto.userPayment.UserPaymentDTOMapper;
import com.trymad.lootmarket.web.dto.userPayment.UserPaymentUpdateDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor

@RestController
@RequestMapping("users/{userId}/payments")
public class UserPaymentController {

    private final UserPaymentDTOMapper upDTOMapper;
    private final UserPaymentService upService;

    @GetMapping
    public List<UserPaymentDTO> getAllByUserId(@PathVariable UUID userId) {
        return upDTOMapper.toDto(upService.getByUserId(userId));
    }

    @PostMapping
    public UserPaymentDTO create(@RequestBody UserPaymentCreateDTO upCreateDto, @PathVariable UUID userId) {
        return upDTOMapper.toDto(upService.create(upCreateDto, userId));
    }

    @PutMapping("{id}")
    public UserPaymentDTO update(@RequestBody UserPaymentUpdateDTO upUpdateDTO, @PathVariable Long id) {
        return upDTOMapper.toDto(upService.update(id, upUpdateDTO));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        upService.delete(id);
    }
}
