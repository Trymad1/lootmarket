package com.trymad.lootmarket.web.dto.userAd;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserAdDTO(
                Long id, Long categoryId, UUID authorId, String title, String text, Integer quantity, int price,
                LocalDateTime createDate, LocalDateTime updateDate) {

}
