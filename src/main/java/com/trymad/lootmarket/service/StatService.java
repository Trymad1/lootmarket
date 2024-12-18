package com.trymad.lootmarket.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.model.Deal;
import com.trymad.lootmarket.web.dto.DealSumDTO;
import com.trymad.lootmarket.web.dto.SiteStatsDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatService {

    private final UserService userService;
    private final DealService dealService;

    @Transactional(readOnly = true)
    public SiteStatsDTO getStatsByDate(LocalDateTime from, LocalDateTime to) {
        if (from == null) {
            from = LocalDateTime.MIN;
        }
        if(to == null) {
            to = LocalDateTime.MAX;
        }
        
        List<LocalDateTime> registrationTimes = userService.getRegistrationDatesByDate(from, to);
        List<LocalDateTime> activitiesTimes = userService.getActivitiesByDate(from, to);
        List<Deal> dealsByDate = dealService.getByDate(from, to);
        List<DealSumDTO> dealSum = dealsByDate.stream()
        .map(deal -> {
            int count = deal.getBuyedQuantity() == null ? 1 : deal.getBuyedQuantity();
            int sum = deal.getService().getPrice() * count;
            return new DealSumDTO(deal.getDealEnd(), sum);
        }).toList();

        return new SiteStatsDTO(activitiesTimes, registrationTimes, dealSum);
    }
}
