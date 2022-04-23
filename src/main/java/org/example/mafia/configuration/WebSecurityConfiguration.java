package org.example.mafia.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mafia.security.HeaderAuthFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${spring.security.enabled}")
    private Boolean enabled;

    @Value("${spring.security.user-role}")
    private String userRole;

    private final AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        if (enabled) {
            http.authorizeRequests().anyRequest().hasRole(userRole)
                    .and()
                    .addFilterAfter(new HeaderAuthFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/swagger-ui.html/**",
                "/swagger-resources/configuration/ui**",
                "/swagger-resources/configuration/security**",
                "/swagger-resources",
                "/webjars/**",
                "/v2/api-docs**",
                "/csrf",
                "/error",
                "/actuator/**",
                "/save",
                "/forbidden",
                "/");
    }

    @PostConstruct
    public void afterPropertiesSet() {
        if (!enabled) {
            log.warn("Spring Security Disabled!");
        }
    }
}
