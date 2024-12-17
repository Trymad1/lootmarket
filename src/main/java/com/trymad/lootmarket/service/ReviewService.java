package com.trymad.lootmarket.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.model.Review;
import com.trymad.lootmarket.repository.review.ReviewRepository;
import com.trymad.lootmarket.web.dto.review.ReviewCreateDTO;
import com.trymad.lootmarket.web.dto.review.ReviewDTOMapper;
import com.trymad.lootmarket.web.dto.review.ReviewUpdateDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewDTOMapper reviewDTOMapper;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<Review> getAll() {
        return reviewRepository.fetchFindAll();
    }

    @Transactional(readOnly = true)
    public Review get(Long id) {
        return reviewRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Review with id " + id + " not found"));
    }

    public List<Review> getByServiceId(Long id) {
        return reviewRepository.fetchFindAllByDealId(id);
    }

    @Transactional(readOnly = true)
    public Double getAvgGradeByServiceId(Long id) {
        return reviewRepository.findAverageGradeByDealId(id);
    }

    @Transactional(readOnly = true)
    public Long getSumReviewsByServiceId(Long id) {
        return reviewRepository.countReviewsByDealId(id);
    }

    @Transactional
    public Review create(ReviewCreateDTO reviewCreateDTO) {
        final Review review = reviewDTOMapper.toEntity(reviewCreateDTO);
        // review.setDeal(userAdService.getById(reviewCreateDTO.dealId()));
        review.setUser(userService.get(reviewCreateDTO.userId()));
        review.setGrade(reviewCreateDTO.grade());
        review.setDate(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Transactional
    public Review update(ReviewUpdateDTO reviewUpdateDTO, Long id) {
        final Review review = this.get(id);
        review.setComment(reviewUpdateDTO.comment());
        review.setGrade(reviewUpdateDTO.grade());

        return reviewRepository.save(review);
    }

    @Transactional
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}
