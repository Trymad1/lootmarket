package com.trymad.lootmarket.web.dto.userAd;

import java.util.UUID;

public record UserAdCreateDTO(Long categoryId, UUID authorId, String title, String text, Integer quantity, int price) {

}
