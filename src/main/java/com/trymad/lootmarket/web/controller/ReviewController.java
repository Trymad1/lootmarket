package com.trymad.lootmarket.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.model.Review;
import com.trymad.lootmarket.service.ReviewService;
import com.trymad.lootmarket.web.dto.review.ReviewCreateDTO;
import com.trymad.lootmarket.web.dto.review.ReviewDTO;
import com.trymad.lootmarket.web.dto.review.ReviewDTOMapper;
import com.trymad.lootmarket.web.dto.review.ReviewUpdateDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewDTOMapper reviewDTOMapper;

    @PostMapping
    public ReviewDTO create(@RequestBody ReviewCreateDTO reviewCreateDTO) {
        return reviewDTOMapper.toDto(reviewService.create(reviewCreateDTO));
    }

    @PutMapping("{id}")
    public ReviewDTO update(@RequestBody ReviewUpdateDTO reviewUpdateDTO, @PathVariable Long id) {
        return reviewDTOMapper.toDto(reviewService.update(reviewUpdateDTO, id));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        reviewService.delete(id);
    }
}
