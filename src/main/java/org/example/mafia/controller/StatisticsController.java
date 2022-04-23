package org.example.mafia.controller;

import lombok.RequiredArgsConstructor;
import org.example.mafia.service.PDFGeneratorHandler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatisticsController {
    private final PDFGeneratorHandler pdfGeneratorHandler;

    @GetMapping(value = "/statistics")
    @Async
    public String getStats(String nickName, @RequestHeader("auth-header") String header) {
        return pdfGeneratorHandler.createPDF(nickName);
    }
}
