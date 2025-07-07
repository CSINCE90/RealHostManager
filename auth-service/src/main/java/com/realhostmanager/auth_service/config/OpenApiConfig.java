package com.realhostmanager.auth_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configurazione OpenAPI per la documentazione Swagger.
 */
@Profile("dev")
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Auth Service API")
                .version("1.0")
                .description("Documentazione delle API del servizio di autenticazione RealHostManager"));
    }
}
