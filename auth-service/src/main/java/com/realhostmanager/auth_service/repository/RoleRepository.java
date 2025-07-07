package com.realhostmanager.auth_service.repository;

import com.realhostmanager.auth_service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository per l'accesso ai dati relativi ai ruoli utente.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
