package org.example.mafia.listener;

import lombok.RequiredArgsConstructor;
import org.example.mafia.service.StatisticsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentQueueListener {
    private final StatisticsService statisticsService;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "documentRequestQueue")
    public void listenRequest(String in) {
        String link = statisticsService.createPDFReport(in);
        rabbitTemplate.convertAndSend("documentResponseQueue", link);
    }
}
