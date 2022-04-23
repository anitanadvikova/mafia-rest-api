package org.example.mafia.service;

import lombok.RequiredArgsConstructor;
import org.example.mafia.builder.GamerBuilder;
import org.example.mafia.entity.Gamer;
import org.example.mafia.model.GamerDto;
import org.example.mafia.repository.GamerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GamerService {
    private final GamerRepository gamerRepository;
    private final GamerBuilder gamerBuilder;

    public GamerDto getGamerByNickname(String nickname) {
        return gamerBuilder.mapGamerDto(gamerRepository.findGamerByNickname(nickname.toLowerCase(Locale.ROOT)));
    }

    public List<GamerDto> getGamers() {
        return gamerRepository.findAll()
                .stream()
                .map(gamerBuilder::mapGamerDto)
                .collect(Collectors.toList());
    }

    public GamerDto update(GamerDto gamer) {
       return Optional.ofNullable(gamerRepository.findGamerByNickname(gamer.getNickname().toLowerCase(Locale.ROOT)))
               .map(g -> {
                   g.setNickname(gamer.getNickname().toLowerCase(Locale.ROOT));
                   g.setSex(gamer.getSex());
                   g.setPic(gamer.getPic());
                   g.setEmail(gamer.getEmail());
                   gamerRepository.saveAndFlush(g);
                           return g;
                       }
               ).map(gamerBuilder::mapGamerDto)
               .orElseThrow();
    }

    public GamerDto save(GamerDto gamer) {
        Gamer newGamer = createNewGamer(gamer);

        Optional.ofNullable(gamerRepository.findGamerByNickname(gamer.getNickname().toLowerCase(Locale.ROOT)))
                .ifPresentOrElse(g -> {
                    throw new RuntimeException("Already exists: " + gamer.getNickname());
                }, (() -> {gamerRepository.saveAndFlush(newGamer);}));

        return gamerBuilder.mapGamerDto(newGamer);
    }

    public String delete(String nickname) {
        Optional.ofNullable(gamerRepository.findGamerByNickname(nickname))
                .ifPresentOrElse(gamerRepository::delete, () -> {throw new RuntimeException("Does not exists: " + nickname);});
        return nickname;
    }

    private Gamer createNewGamer(GamerDto gamer) {
        return Gamer.builder()
                .nickname(gamer.getNickname().toLowerCase(Locale.ROOT))
                .sex(gamer.getSex())
                .pic(gamer.getPic())
                .email(gamer.getEmail())
                .build();
    }
}