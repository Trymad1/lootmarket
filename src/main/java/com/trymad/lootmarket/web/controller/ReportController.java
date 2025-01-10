package com.trymad.lootmarket.web.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.trymad.lootmarket.service.ReportGeneratorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("api/report")
public class ReportController {
	@Autowired
	private ReportGeneratorService reportGeneratorService;

	@GetMapping("/deals")
	public ResponseEntity<byte[]> generateReport(@RequestParam(required = false) LocalDateTime from, @RequestParam(required = false) LocalDateTime to) throws IOException, DocumentException {
        if(from == null) from = LocalDateTime.of(2020, 1, 1, 1, 1);
        if(to == null) to = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        byte[] pdfBytes = reportGeneratorService.generateReport(from, to);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", String.format("deal-report-%s-%s.pdf", formatter.format(from), formatter.format(to)));

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
