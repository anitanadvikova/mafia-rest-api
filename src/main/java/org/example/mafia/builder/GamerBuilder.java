package org.example.mafia.builder;

import org.example.mafia.entity.Gamer;
import org.example.mafia.model.*;
import org.springframework.stereotype.Component;

@Component
public class GamerBuilder {
    public GamerDto mapGamerDto(Gamer gamer) {
        return GamerDto.builder()
                .nickname(gamer.getNickname())
                .pic(gamer.getPic())
                .sex(gamer.getSex())
                .email(gamer.getEmail())
                .build();
    }
}
