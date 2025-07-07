package com.realhostmanager.auth_service.controller;

import com.realhostmanager.auth_service.dto.request.LoginRequest;
import com.realhostmanager.auth_service.dto.request.RefreshTokenRequest;
import com.realhostmanager.auth_service.dto.response.AuthResponse;
import com.realhostmanager.auth_service.model.RefreshToken;
import com.realhostmanager.auth_service.model.User;
import com.realhostmanager.auth_service.service.RefreshTokenService;
import com.realhostmanager.auth_service.service.UserService;
import com.realhostmanager.auth_service.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Controller per la gestione dell'autenticazione degli utenti.
 * Espone endpoint per login e, in futuro, per registrazione e refresh token.
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService; // Aggiungi JwtService come campo
    private final RefreshTokenService refreshTokenService;

    /**
     * Effettua il login dell'utente sulla base delle credenziali fornite.
     * Restituisce un token JWT e i dati essenziali dell'utente.
     *
     *  param request oggetto contenente email e password
     *  return JWT, ID utente e ruolo se autenticazione avvenuta con successo
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest request) {
        User user = userService.getUserByEmail(request.getEmail());

        if (user == null || !userService.passwordMatches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Credenziali non valide");
        }

        String token = jwtService.generateToken(user);
        AuthResponse response = new AuthResponse(token, user.getId(), user.getRole().getName());
        return ResponseEntity.ok(response);
    }

    /**
     * Genera un nuovo access token a partire da un refresh token valido.
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshAccessToken(@Valid @RequestBody RefreshTokenRequest request) {
        return refreshTokenService.findByToken(request.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtService.generateToken(user);
                    return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getRole().getName()));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(null, null, null)));
    }
}
