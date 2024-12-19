package com.trymad.lootmarket.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DealSumDTO {
    
    private final LocalDateTime time;
    private final Long sum;
}
