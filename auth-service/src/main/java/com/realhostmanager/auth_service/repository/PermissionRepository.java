package com.realhostmanager.auth_service.repository;

import com.realhostmanager.auth_service.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository per la gestione delle entità Permission.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
