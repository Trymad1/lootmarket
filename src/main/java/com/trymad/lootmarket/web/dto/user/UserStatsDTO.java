package com.trymad.lootmarket.web.dto.user;

import java.time.LocalDateTime;
import java.util.List;

public record UserStatsDTO(Long totalServicesPosted, Long totalServicesSold, Long totalServicesPurchased,
                        List<String> paymentSystems, List<LocalDateTime> activityDates,
                        List<LocalDateTime> serviceSaleDates, List<LocalDateTime> servicePurchaseDates) {

                        }
