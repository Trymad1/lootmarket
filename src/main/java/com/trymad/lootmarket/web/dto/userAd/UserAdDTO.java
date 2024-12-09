package com.trymad.lootmarket.web.dto.userAd;

import java.time.LocalDateTime;
import java.util.UUID;

import com.trymad.lootmarket.model.Review;
import com.trymad.lootmarket.web.dto.deal.DealDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
        double avgGrade;
        LocalDateTime createDate;
        LocalDateTime updateDate;
}
