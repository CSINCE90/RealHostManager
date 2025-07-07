package com.realhostmanager.auth_service.repository;

import com.realhostmanager.auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository per l'accesso ai dati relativi all'entità User.
 * Fornisce metodi per recuperare e verificare la presenza di utenti in base all'email.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    // Recupera un utente tramite la sua email
    Optional<User> findByEmail(String email);

    // Verifica se un utente esiste già con la specifica email
    boolean existsByEmail(String email);
}
