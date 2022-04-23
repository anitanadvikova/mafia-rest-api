package org.example.mafia.service;

import lombok.RequiredArgsConstructor;
import org.example.mafia.entity.Gamer;
import org.example.mafia.repository.GamerRepository;
import org.example.mafia.security.CustomGrantedAuthority;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthService {
    @Value("${spring.security.user-role}")
    private String role;
    private final GamerRepository gamerRepository;

    public Set<? extends GrantedAuthority> getToken(String header) {
        return List.of(header)
                .stream()
                .map(pa -> {
                    final Gamer gamerByNickname = gamerRepository.findGamerByNickname(header.toLowerCase(Locale.ROOT));
                    return Optional.ofNullable(gamerByNickname)
                            .map(g -> new CustomGrantedAuthority("ROLE_" + role))
                            .orElse(new CustomGrantedAuthority("FORBIDDEN"));
                        }
                )
                .collect(Collectors.toSet());
    }
}
