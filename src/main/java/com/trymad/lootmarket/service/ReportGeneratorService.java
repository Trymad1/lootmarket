package com.trymad.lootmarket.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Service
public class ReportGeneratorService {

    public byte[] generateReport() throws DocumentException, IOException {
        // Создание документа PDF
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
    
        // Открываем документ для записи
        document.open();
    
        // Используем шрифт, поддерживающий кириллицу (например, DejaVuSans)
        ClassPathResource resource = new ClassPathResource("DejaVuSans.ttf");
        
        // Получаем путь к файлу в resources
        String fontPath = resource.getPath();
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 10, Font.NORMAL);
        
        // Добавление заголовка
        Paragraph title = new Paragraph("Отчёт по сделкам", new Font(baseFont, 16, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
    
        // Параграф с датами
        Paragraph period = new Paragraph("Период: 2023-01-01 — 2023-01-31", new Font(baseFont, 12, Font.NORMAL));
        period.setAlignment(Element.ALIGN_CENTER);
        document.add(period);
    
        // Добавление пустой строки
        document.add(new Chunk("\n"));
        addSummaryBlock(document, font);
        // Таблица с данными
        PdfPTable table = createTable(font);
    
        // Добавление таблицы в документ
        document.add(table);
    
        // Закрываем документ
        document.close();
    
        // Возвращаем байтовый массив
        return byteArrayOutputStream.toByteArray();
    }

    // Метод для добавления блока с данными
    private void addSummaryBlock(Document document, Font font) throws DocumentException {
        // Генерация случайных данных для отчета
        int totalDeals = randomInt(50, 200);
        int turnover = randomInt(5000, 100000);
        int income = randomInt(1000, 20000);
        int canceled = randomInt(5, 20);
        int completed = randomInt(20, 150);
        int inProcess = randomInt(5, 30);

        // Блок с общими данными
        Paragraph summaryTitle = new Paragraph("Общие данные", new Font(font.getBaseFont(), 14, Font.BOLD));
        summaryTitle.setAlignment(Element.ALIGN_LEFT);
        document.add(summaryTitle);

        // Параграф с общей статистикой
        Paragraph summary = new Paragraph(
            String.format("Общее число сделок: %d", totalDeals), font);
        document.add(summary);
        summary = new Paragraph(
            String.format("Денежный оборот: %d руб.", turnover), font);
        document.add(summary);
        summary = new Paragraph(
            String.format("Доход: %d руб.", income), font);
        document.add(summary);

        // Статистика по статусам
        summary = new Paragraph(
            String.format("Отменено: %d | Закрыто: %d | В процессе: %d", canceled, completed, inProcess), font);
        document.add(summary);

        // Добавление пустой строки
        document.add(new Chunk("\n"));
    }

    private PdfPTable createTable(Font font) throws DocumentException {
        // Создание таблицы (8 столбцов)
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);

        // Устанавливаем ширину столбцов
        float[] columnWidths = { 1f, 2f, 2f, 1f, 2f, 2f, 2f, 2f };
        table.setWidths(columnWidths);

        // Добавление заголовков таблицы
        table.addCell(createCell("ID", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Продавец", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Покупатель", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Кол-во", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Платёжная система", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Статус сделки", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Дата начала", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Дата Закрытия", font, Element.ALIGN_CENTER));

        // Добавление 100 строк с данными
        for (int i = 0; i < 100; i++) {
            table.addCell(createCell(String.valueOf(i + 1), font, Element.ALIGN_CENTER));
            table.addCell(createCell("Продавец " + randomInt(1, 20), font, Element.ALIGN_CENTER));
            table.addCell(createCell("Покупатель " + randomInt(1, 30), font, Element.ALIGN_CENTER));
            table.addCell(createCell(String.valueOf(randomInt(1, 100)), font, Element.ALIGN_CENTER));
            table.addCell(createCell(randomPaymentSystem(), font, Element.ALIGN_CENTER));
            table.addCell(createCell(randomStatus(), font, Element.ALIGN_CENTER));
            table.addCell(createCell(randomDate("2023-01-01", "2023-12-31"), font, Element.ALIGN_CENTER));
            table.addCell(createCell(randomDate("2024-01-01", "2024-12-31"), font, Element.ALIGN_CENTER));
        }

        return table;
    }

    // Метод для создания ячеек таблицы
    private PdfPCell createCell(String content, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        cell.setBorderWidth(0.5f);
        return cell;
    }

    // Метод для генерации случайных чисел в диапазоне
    private int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    // Метод для генерации случайной даты в пределах заданного диапазона
    private String randomDate(String startDate, String endDate) {
        long start = java.sql.Date.valueOf(startDate).getTime();
        long end = java.sql.Date.valueOf(endDate).getTime();
        long randomTime = start + (long) (Math.random() * (end - start));
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(randomTime));
    }

    // Метод для генерации случайной платежной системы
    private String randomPaymentSystem() {
        String[] paymentSystems = {"Visa", "MasterCard", "PayPal", "Яндекс.Деньги", "QIWI"};
        return paymentSystems[new Random().nextInt(paymentSystems.length)];
    }

    // Метод для генерации случайного статуса
    private String randomStatus() {
        String[] statuses = {"Завершено", "Отменено", "В процессе"};
        return statuses[new Random().nextInt(statuses.length)];
    }
}
