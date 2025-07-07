package com.realhostmanager.gatewayservice.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.*;

@Configuration
public class JwtDecoderConfig {

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        String secretKey = "myTopSecretKey"; // o recupera da env
        return NimbusReactiveJwtDecoder.withSecretKey(
            new SecretKeySpec(secretKey.getBytes(), "HmacSHA256")
        ).build();
    }
}