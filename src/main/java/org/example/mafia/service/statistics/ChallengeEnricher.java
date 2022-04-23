package org.example.mafia.service.statistics;

import lombok.RequiredArgsConstructor;
import org.example.mafia.entity.Challenge;
import org.example.mafia.repository.ChallengeRepository;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ChallengeEnricher implements TemplateEnricher {
    private final ChallengeRepository challengeRepository;

    @Override
    public Map<String, Object> enrich(Map<String, Object> map, String gamerId) {
        final List<Challenge> challenges = getChallenges(gamerId);

        countGames(challenges, map);
        countWinnings(challenges, map, gamerId);
        countTime(challenges, map);

        return map;
    }

    private List<Challenge> getChallenges(String gamerId) {
        return challengeRepository.findAllByGamerId(UUID.fromString(gamerId));
    }

    private Map<String, Object> countGames(List<Challenge> challenges, Map<String, Object> map) {
        map.put("gamesCount", String.valueOf(challenges.size()));
        return map;
    }

    private Map<String, Object> countWinnings(List<Challenge> challenges, Map<String, Object> map, String gamerId) {
        final long size = challenges.stream()
                .filter(c -> c.getWinner().equals(UUID.fromString(gamerId))).count();

        map.put("winnings", String.valueOf(size));
        return map;
    }

    private Map<String, Object> countTime(List<Challenge> challenges, Map<String, Object> map) {
        Duration duration = Duration.ZERO;
        for (Challenge challenge : challenges) {
            duration = duration.plus(Duration.between(challenge.getStartDate(), challenge.getEndDate()));
        }
        map.put("duration", String.valueOf(duration.getSeconds() / 60));

        return map;
    }
}
