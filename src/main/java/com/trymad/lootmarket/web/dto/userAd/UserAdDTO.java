package com.trymad.lootmarket.web.dto.userAd;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAdDTO {

        Long id;
        Long categoryId;
        String categoryName;
        UUID authorId;
        String authorName;
        String title;
        String text;
        Integer quantity;
        int price;
        LocalDateTime createDate;
        LocalDateTime updateDate;
}
