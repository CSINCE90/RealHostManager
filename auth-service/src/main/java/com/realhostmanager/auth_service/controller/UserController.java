package com.realhostmanager.auth_service.controller;

import com.realhostmanager.auth_service.model.User;
import com.realhostmanager.auth_service.service.UserService;
import com.realhostmanager.auth_service.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller per gestire le operazioni dell'utente autenticato.
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Restituisce le informazioni dell'utente autenticato.
     *
     * @return Dati dell'utente autenticato
     */
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        String email = SecurityUtils.getCurrentUserEmail();
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    /**
     * Aggiorna i dati del profilo dell'utente autenticato.
     *
     * @. param updatedUser Nuovi dati da salvare
     * @. return Utente aggiornato
     */
    @PutMapping("/me")
    public ResponseEntity<User> updateProfile(@RequestBody User updatedUser) {
        String email = SecurityUtils.getCurrentUserEmail();
        User updated = userService.updateUser(email, updatedUser);
        return ResponseEntity.ok(updated);
    }
}
