package org.example.mafia.service.statistics;

import lombok.RequiredArgsConstructor;
import org.example.mafia.entity.Gamer;
import org.example.mafia.repository.GamerRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GamerEnricher implements TemplateEnricher {
    private final GamerRepository gamerRepository;

    @Override
    @Transactional
    public Map<String, Object> enrich(Map<String, Object> map, String gamerId) {
        final Gamer gamer = getGamer(gamerId);

        map.put("name", gamer.getNickname());
        map.put("sex", gamer.getSex());
        map.put("email", gamer.getEmail());
        map.put("picture", gamer.getPic());

        return map;
    }

    private Gamer getGamer(String gamerId) {
        return gamerRepository.getById(UUID.fromString(gamerId));
    }
}
