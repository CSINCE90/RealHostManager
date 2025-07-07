package com.realhostmanager.auth_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;
import java.util.List;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.realhostmanager.auth_service.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
/**
 * Configurazione di sicurezza dell'applicazione.
 * Definisce il filtro JWT, le policy CORS, il gestore di autenticazione
 * e la configurazione della catena di filtri di Spring Security.
 */
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Configura la catena dei filtri di sicurezza di Spring Security.
     * - Disabilita CSRF
     * - Abilita CORS
     * - Imposta rotte pubbliche e protette
     * - Inserisce il filtro JWT
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Encoder per la cifratura delle password.
     * Utilizza BCrypt come algoritmo sicuro.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Espone il bean di AuthenticationManager per l'autenticazione personalizzata.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configura le policy CORS per permettere le chiamate dal frontend.
     * Qui è abilitato solo il dominio localhost:5173.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(ALLOWED_ORIGINS);
        cfg.setAllowedMethods(ALLOWED_METHODS);
        cfg.addAllowedHeader("*");
        cfg.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", cfg);
        return source;
    }

    private static final List<String> ALLOWED_ORIGINS = List.of("http://localhost:5173");
    private static final List<String> ALLOWED_METHODS = List.of("GET", "POST", "PUT", "DELETE", "OPTIONS");

    /*
     * La classe SecurityConfig definisce la configurazione di sicurezza per il microservizio auth-service.
     * Comprende:
     * - La configurazione della catena dei filtri di Spring Security con supporto per JWT.
     * - La disabilitazione di CSRF e la configurazione stateless delle sessioni.
     * - La configurazione CORS per accettare richieste da frontend autorizzati.
     * - L'esposizione dei bean PasswordEncoder e AuthenticationManager necessari all'autenticazione.
     */
}
