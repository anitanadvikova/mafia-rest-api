package org.example.mafia.service;

import lombok.RequiredArgsConstructor;
import org.example.mafia.entity.Gamer;
import org.example.mafia.repository.GamerRepository;
import org.example.mafia.service.statistics.TemplateEnricher;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final List<TemplateEnricher> enricherList;
    private final PDFGenerator pdfGenerator;
    private final AmazonS3Service amazonS3Service;
    private final GamerRepository gamerRepository;

    public String createPDFReport(String fileName) {
        String nickName = fileName.split("\\.")[0];
        final Gamer gamer = gamerRepository.findGamerByNickname(nickName);
        final Map<String, Object> map = getMap(gamer.getId().toString());
        return generateFile(map, fileName);
    }


    private Map<String, Object> getMap(String id) {
        Map<String, Object> map = new HashMap<>();
        enricherList.forEach(el -> el.enrich(map, id));
        return map;
    }

    private String generateFile(Map<String, Object> map, String fileName) {
        try {
            final ByteArrayOutputStream generate = pdfGenerator.generate(map);
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(generate.toByteArray());

            fileName = UUID.randomUUID() + "_" + fileName;

            amazonS3Service.putInputStream(fileName, byteArrayInputStream);
            byteArrayInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return amazonS3Service.getLink(fileName);
    }
}
