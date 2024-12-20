package com.trymad.lootmarket.web.dto.review;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewDTO(
    Long id, 
    UUID userId,
    String author, 
    Long dealId, 
    int grade, 
    String comment, 
    LocalDateTime date) {

}
