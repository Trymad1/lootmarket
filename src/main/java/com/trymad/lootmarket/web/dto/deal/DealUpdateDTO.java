package com.trymad.lootmarket.web.dto.deal;

import java.time.LocalDateTime;

import com.trymad.lootmarket.model.DealStatus;

public record DealUpdateDTO(DealStatus dealStatus, Integer buyedQuantity, LocalDateTime dealEnd) {

}
