package com.trymad.lootmarket.web.controller;

import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.model.PaymentSystem;
import com.trymad.lootmarket.service.PaymentSystemService;
import com.trymad.lootmarket.web.dto.paymentSystem.PaymentSystemCreateDTO;
import com.trymad.lootmarket.web.dto.paymentSystem.PaymentSystemDTO;
import com.trymad.lootmarket.web.dto.paymentSystem.PaymentSystemDTOMapper;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor

@RestController
@RequestMapping("paymentSystems")
public class PaymentSystemController {

    private final PaymentSystemService psService;
    private final PaymentSystemDTOMapper psDTOMapper;

    @GetMapping
    public List<PaymentSystemDTO> getAll() {
        return psDTOMapper.toDto(psService.getAll());
    }

    @GetMapping("{id}")
    public PaymentSystemDTO getById(@PathVariable Long id) {
        return psDTOMapper.toDto(psService.get(id));
    }

    @PostMapping
    public PaymentSystemDTO create(@RequestBody PaymentSystemCreateDTO psCreateDTO) {
        return psDTOMapper.toDto(psService.create(psCreateDTO));
    }

    @PutMapping("{id}")
    public PaymentSystemDTO update(@PathVariable Long id, @RequestBody PaymentSystemCreateDTO psUpdateDTO) {
        final PaymentSystem ps = psDTOMapper.toEntity(psUpdateDTO);
        ps.setId(id);
        return psDTOMapper.toDto(psService.update(ps));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        psService.delete(id);
    }

}
