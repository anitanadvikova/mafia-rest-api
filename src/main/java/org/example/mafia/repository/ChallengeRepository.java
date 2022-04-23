package org.example.mafia.repository;

import org.example.mafia.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ChallengeRepository extends JpaRepository<Challenge, UUID> {
    @Query(value = "SELECT * FROM challenges WHERE :gamerId = ANY (gamers)", nativeQuery = true)
    List<Challenge> findAllByGamerId(UUID gamerId);
}
