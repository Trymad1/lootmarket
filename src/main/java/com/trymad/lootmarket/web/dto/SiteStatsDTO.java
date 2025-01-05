package com.trymad.lootmarket.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record SiteStatsDTO(
    List<LocalDateTime> activities, 
    List<LocalDateTime> registration, 
    List<LocalDateTime> services,
    List<DealSumDTO> deals) {

}
