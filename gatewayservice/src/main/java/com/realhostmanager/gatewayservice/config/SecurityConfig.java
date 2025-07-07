package com.realhostmanager.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
            .csrf().disable()
            .authorizeExchange()
            .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Consenti tutte le OPTIONS
            .pathMatchers("/api/auth/**", "/api/admin/register").permitAll() // Auth e register pubblici
            .anyExchange().authenticated() // Tutto il resto protetto
            .and()
            .oauth2ResourceServer()
            .jwt()
            .and().and()
            .build();
    }
}