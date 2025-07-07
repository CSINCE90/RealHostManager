package com.realhostmanager.auth_service.repository;

import com.realhostmanager.auth_service.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository per la gestione dei refresh token.
 * Permette operazioni CRUD e query personalizzate sul token.
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    // Cerca un refresh token specifico tramite il suo valore stringa
    Optional<RefreshToken> findByToken(String token);
    // Elimina tutti i refresh token associati a uno specifico utente
    void deleteByUserId(Long userId);
}
