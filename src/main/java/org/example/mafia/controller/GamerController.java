package org.example.mafia.controller;

import lombok.RequiredArgsConstructor;
import org.example.mafia.entity.Gamer;
import org.example.mafia.model.GamerDto;
import org.example.mafia.service.GamerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GamerController {
    private final GamerService gamerService;

    @GetMapping(value = "/getGamerByNickname")
    public GamerDto getGamerById(String nickname, @RequestHeader("auth-header") String header) {
        return gamerService.getGamerByNickname(nickname);
    }

    @GetMapping(value = "/getGamers")
    public List<GamerDto> getGamers(@RequestHeader("auth-header") String header) {
        return gamerService.getGamers();
    }

    @PostMapping(value = "/update")
    public GamerDto update(@RequestBody  GamerDto gamer, @RequestHeader("auth-header") String header) {
        return gamerService.update(gamer);
    }

    @PostMapping(value = "/save")
    public GamerDto save(@RequestBody GamerDto gamer) {
        return gamerService.save(gamer);
    }

    @DeleteMapping(value = "/delete")
    public String delete(String nickname, @RequestHeader("auth-header") String header) {
        return gamerService.delete(nickname);
    }
}
