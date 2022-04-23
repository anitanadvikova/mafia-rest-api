package org.example.mafia.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class HeaderAuthFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("auth-header");
        log.info("User: " + header + " triggered authentication for URL: " + request.getRequestURI());
        SecurityContextHolder.getContext().setAuthentication(new UserAuth(header));
        filterChain.doFilter(request, response);
    }
}
