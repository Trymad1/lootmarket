package com.trymad.lootmarket.web.dto.userDto;

import java.time.LocalDateTime;
import java.util.List;

import com.trymad.lootmarket.model.PaymentSystem;
import com.trymad.lootmarket.web.dto.paymentSystem.PaymentSystemDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserStatsDTO {
    private Long servicesPosted;
    private Long servicesSold;
    private Long servicesPurchased;
    private List<PaymentSystemDTO> paymentSystems;
    private List<LocalDateTime> activityDates;
    private List<LocalDateTime> serviceSaleDates;
    private List<LocalDateTime> servicePurchaseDates;
}

