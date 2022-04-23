package org.example.mafia.security;

import lombok.RequiredArgsConstructor;
import org.example.mafia.service.AuthService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final AuthService authorizationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Set<? extends GrantedAuthority> authorities;

        if (authentication.getName() != null && !authentication.getName().equals("")) {
            authorities = authorizationService.getToken(authentication.getName());
            authentication.setAuthenticated(true);
        } else {
            authorities = Collections.emptySet();
        }

        if (authentication.isAuthenticated()) {
            return new UsernamePasswordAuthenticationToken(
                    authentication.getName(), authentication.getName(), authorities);
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}