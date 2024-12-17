package com.trymad.lootmarket.web.dto.deal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.trymad.lootmarket.model.DealStatus;

public record DealDTO(
                Long id,
                Long serviceId,
                UUID buyerId,
                String buyerName,
                Long paymentSystemId,
                String paymentSystemName,
                DealStatus dealStatus,
                Integer buyedQuantity,
                LocalDateTime dealStart,
                LocalDate dealEnd) {

}
