package com.trymad.lootmarket.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<byte[]> generateReport() throws IOException, DocumentException {
        // Генерируем PDF отчет
        byte[] pdfBytes = reportGeneratorService.generateReport();

        // Настройки заголовков для ответа
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "report.pdf");

        // Возвращаем PDF файл в ответе
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
