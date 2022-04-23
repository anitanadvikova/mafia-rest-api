package org.example.mafia.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class PDFGeneratorHandler {
    private final RabbitTemplate rabbitTemplate;

    public String createPDF(String nickname) {
        String fileName = nickname.toLowerCase(Locale.ROOT) + ".pdf";
        rabbitTemplate.convertAndSend("documentRequestQueue", fileName);
        return getResultQueue();
    }

    @SneakyThrows
    private String getResultQueue() {
        Message documentResponseQueue = rabbitTemplate.receive("documentResponseQueue");
        while (documentResponseQueue == null) {
            Thread.sleep(1000);
            documentResponseQueue = rabbitTemplate.receive("documentResponseQueue");
        }
        return new String(documentResponseQueue.getBody(), StandardCharsets.UTF_8);
    }
}
