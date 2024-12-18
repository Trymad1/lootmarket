package com.trymad.lootmarket.web.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.service.StatService;
import com.trymad.lootmarket.web.dto.SiteStatsDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/stats")
public class StatController {
    
    private final StatService statService;

    @GetMapping
    public SiteStatsDTO getStats(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
        return statService.getStatsByDate(from, to);
    }
}
