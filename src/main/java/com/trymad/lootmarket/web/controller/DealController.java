package com.trymad.lootmarket.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.service.DealService;
import com.trymad.lootmarket.web.dto.deal.DealCreateDTO;
import com.trymad.lootmarket.web.dto.deal.DealDTO;
import com.trymad.lootmarket.web.dto.deal.DealDTOMapper;
import com.trymad.lootmarket.web.dto.deal.DealUpdateDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("api/deals")
public class DealController {

    private final DealService dealService;
    private final DealDTOMapper dealDTOMapper;

    @GetMapping
    public List<DealDTO> getAll() {
        return dealDTOMapper.toDto(dealService.getAll());
    }

    @GetMapping("{id}") 
    public List<DealDTO> getAllByServiceId(@PathVariable Long id) {
        return dealDTOMapper.toDto(dealService.getByServiceId(id));
    }

    @PostMapping
    public DealDTO create(@RequestBody DealCreateDTO dealCreateDTO) {
        return dealDTOMapper.toDto(dealService.create(dealCreateDTO));
    }

    @PutMapping("{id}")
    public DealDTO update(@RequestBody DealUpdateDTO dealUpdateDTO, @PathVariable Long id) {
        return dealDTOMapper.toDto(dealService.update(dealUpdateDTO, id));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        dealService.delete(id);
    }
}
