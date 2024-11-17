package com.trymad.lootmarket.web.dto.review;

import java.util.UUID;

public record ReviewCreateDTO(UUID userId, Long dealId, int grade, String comment) {
    
}
