package com.realhostmanager.auth_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configurazione delle proprietà JWT mappate da application.yml.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    /**
     * Chiave segreta utilizzata per firmare i token JWT.
     */
    private String secret;

    /**
     * Durata del token in millisecondi (es. 86400000 = 1 giorno).
     */
    private long expiration = 86400000;

    /**
     * Issuer del token, ovvero chi lo ha generato.
     */
    private String issuer = "realhostmanager-auth";
}
