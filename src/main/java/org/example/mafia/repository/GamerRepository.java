package org.example.mafia.repository;

import org.example.mafia.entity.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GamerRepository extends JpaRepository<Gamer, UUID> {
    Gamer findGamerByNickname(String nickName);
}
