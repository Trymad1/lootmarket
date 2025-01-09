package com.trymad.lootmarket.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.trymad.lootmarket.model.Deal;
import com.trymad.lootmarket.model.DealStatus;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ReportGeneratorService {

    private final DealService dealService;

    public byte[] generateReport(LocalDateTime from, LocalDateTime to) throws DocumentException, IOException {
        DateTimeFormatter formatterOnlyData = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        List<Deal> deals = dealService.getByDate(from, to, true);
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();
        
        ClassPathResource resource = new ClassPathResource("DejaVuSans.ttf");
        
        String fontPath = resource.getPath();
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 10, Font.NORMAL);
        
        writer.setPageEvent(new PdfPageEventHelper() {
            @Override
            public void onEndPage(PdfWriter writer, Document document) {
                int pageNumber = writer.getPageNumber();

				Font fontPage = new Font(baseFont, 10, Font.NORMAL);

				String text = pageNumber + "";
				float x = (document.getPageSize().getWidth() - font.getBaseFont().getWidthPoint(text, fontPage.getSize())) / 2;
				float y = document.bottom() - 10;

				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(text, fontPage), x, y, 0);
            }
        });

        Paragraph title = new Paragraph("Отчёт по сделкам", new Font(baseFont, 16, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
    
        Paragraph period = new Paragraph(
            String.format("Период: %s — %s", formatterOnlyData.format(from), formatterOnlyData.format(to)), new Font(baseFont, 12, Font.NORMAL));
        period.setAlignment(Element.ALIGN_CENTER);
        document.add(period);
    
        document.add(new Chunk("\n"));
        PdfPTable table = createTable(font);
        final Map<String, Double> stats = getStats(deals, table, font);
        addSummaryBlock(document, font, stats);
    

        document.add(table);
        document.close();
    
        return byteArrayOutputStream.toByteArray();
    }

    private Map<String, Double> getStats(List<Deal> deals, PdfPTable table, Font font) {
        HashMap<String, Double> stats = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");

        stats.put("total", 0d);
        stats.put("turnover", 0d);
        stats.put("income", 0d);
        stats.put("CANCELLED", 0d);
        stats.put("COMPLETE", 0d);
        stats.put("IN_PROGRESS", 0d);

        deals.forEach(deal -> {
            int count = deal.getBuyedQuantity() == null ? 1 : deal.getBuyedQuantity();
            Long sum = (long) deal.getService().getPrice() * count;
            final DealStatus dealStatus = deal.getDealStatus().getName();

            stats.replace("total", stats.get("total") + 1);
            if(deal.getDealEnd() != null) {
                stats.replace("turnover", stats.get("turnover") + sum);
                stats.replace("income", stats.get("income") + sum * 0.15);
            }
            stats.replace(dealStatus.toString(), stats.get(dealStatus.toString()) + 1);

            String statusName = "null";
            if(dealStatus == DealStatus.COMPLETE) {
                statusName = "Завершена";
            } else if(dealStatus == DealStatus.CANCELLED) {
                statusName = "Отменена";
            } else if(dealStatus == DealStatus.IN_PROGRESS) {
                statusName = "В процессе";
            }

            String dealStart = formatter.format(deal.getDealStart());
            String dealEnd = deal.getDealEnd() == null ? "-" : formatter.format(deal.getDealEnd());
            table.addCell(createCell(deal.getId().toString(), font, Element.ALIGN_CENTER));
            table.addCell(createCell(deal.getService().getAuthor().getName(), font, Element.ALIGN_CENTER));
            table.addCell(createCell(deal.getBuyer().getName(), font, Element.ALIGN_CENTER));
            table.addCell(createCell(deal.getBuyedQuantity() == null ? "1" : deal.getBuyedQuantity().toString(), font, Element.ALIGN_CENTER));
            table.addCell(createCell(deal.getPaymentSystem().getName(), font, Element.ALIGN_CENTER));
            table.addCell(createCell(statusName, font, Element.ALIGN_CENTER));
            table.addCell(createCell(dealStart, font, Element.ALIGN_CENTER));
            table.addCell(createCell(dealEnd, font, Element.ALIGN_CENTER));

        });

        return stats;
    }

    private void addSummaryBlock(Document document, Font font, Map<String, Double> stats) throws DocumentException {
        Paragraph summaryTitle = new Paragraph("Общие данные", new Font(font.getBaseFont(), 14, Font.BOLD));
        summaryTitle.setAlignment(Element.ALIGN_LEFT);
        document.add(summaryTitle);
        Paragraph summary = new Paragraph(
            String.format("Общее число сделок: %d", Math.round(stats.get("total"))), font);
        document.add(summary);
        summary = new Paragraph(
            String.format("Денежный оборот: %d руб.", Math.round(stats.get("turnover"))), font);
        document.add(summary);
        summary = new Paragraph(
            String.format("Доход: %d руб.", Math.round(stats.get("income"))), font);
        document.add(summary);

        summary = new Paragraph(
            String.format("Отменено: %d | Закрыто: %d | В процессе: %d", 
            Math.round(stats.get(DealStatus.CANCELLED.toString())), 
            Math.round(stats.get(DealStatus.COMPLETE.toString())),
            Math.round(stats.get(DealStatus.IN_PROGRESS.toString()))), font);
        document.add(summary);

        document.add(new Chunk("\n"));
    }

    private PdfPTable createTable(Font font) throws DocumentException {
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);

        float[] columnWidths = { 1f, 2f, 2f, 1f, 2f, 2f, 2f, 2f };
        table.setWidths(columnWidths);

        table.addCell(createCell("ID", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Продавец", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Покупатель", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Кол-во", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Платёжная система", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Статус сделки", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Дата начала", font, Element.ALIGN_CENTER));
        table.addCell(createCell("Дата Закрытия", font, Element.ALIGN_CENTER));

        return table;
    }

    private PdfPCell createCell(String content, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        cell.setBorderWidth(0.5f);
        return cell;
    }
}
