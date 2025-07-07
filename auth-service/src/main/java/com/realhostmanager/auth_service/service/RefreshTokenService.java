package com.realhostmanager.auth_service.service;

import com.realhostmanager.auth_service.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    
    /**
     * Crea un nuovo refresh token per un determinato userId.
     */
    RefreshToken createRefreshToken(Long userId);

    /**
     * Trova un token esistente tramite la stringa del token.
     */
    Optional<RefreshToken> findByToken(String token);

    /**
     * Verifica se il token è scaduto e solleva eccezione se necessario.
     */
    RefreshToken verifyExpiration(RefreshToken token);

    /**
     * Elimina tutti i refresh token associati a un determinato utente.
     */
    void deleteByUserId(Long userId);
}
