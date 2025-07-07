package com.realhostmanager.auth_service.service;

import com.realhostmanager.auth_service.dto.request.LoginRequest;
import com.realhostmanager.auth_service.dto.request.RegisterRequest;
import com.realhostmanager.auth_service.dto.response.AuthResponse;

public interface AuthService {

    /**
     * Registra un nuovo utente e restituisce un token JWT insieme ai dati essenziali.
     */
    AuthResponse register(RegisterRequest request);

    /**
     * Autentica un utente sulla base delle credenziali fornite.
     */
    AuthResponse login(LoginRequest request);
}
