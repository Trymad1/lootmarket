package com.trymad.lootmarket.service;

import java.time.LocalDateTime;
import java.util.List;

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
    private final UserAdService userAdService;

    @Transactional(readOnly = true)
    public SiteStatsDTO getStatsByDate(LocalDateTime from, LocalDateTime to) {
        if (from == null) {
            from = LocalDateTime.of(2000, 1, 1, 1, 1, 1);
        }
        if(to == null) {
            to = LocalDateTime.now();
        }
        
        List<LocalDateTime> registrationTimes = userService.getRegistrationDatesByDate(from, to);
        List<LocalDateTime> activitiesTimes = userService.getActivitiesByDate(from, to);
        List<LocalDateTime> servicesTimes = userAdService.getServicesByDate(from,to);
        List<Deal> dealsByDate = dealService.getByDate(from, to, false);
        List<DealSumDTO> dealSum = dealsByDate.stream()
        .map(deal -> {
            int count = deal.getBuyedQuantity() == null ? 1 : deal.getBuyedQuantity();
            Long sum = (long) deal.getService().getPrice() * count;
            return new DealSumDTO(deal.getDealEnd(), sum);
        }).toList();

        return new SiteStatsDTO(activitiesTimes, registrationTimes, servicesTimes, dealSum);
    }
}
