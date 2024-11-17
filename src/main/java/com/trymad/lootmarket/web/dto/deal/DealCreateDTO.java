package com.trymad.lootmarket.web.dto.deal;

import java.util.UUID;

import com.trymad.lootmarket.model.DealStatus;

public record DealCreateDTO(Long serviceId, UUID buyerId, Long paymentSystemId, DealStatus dealStatus,
        Integer buyedQuantity) {

}
