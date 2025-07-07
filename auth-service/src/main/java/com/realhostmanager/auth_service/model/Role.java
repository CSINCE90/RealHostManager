package com.realhostmanager.auth_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Entità che rappresenta un ruolo assegnabile a uno o più utenti.
 * Un ruolo può essere associato a più permessi.
 */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    public static final String ADMIN = "ADMIN";
    public static final String HOSTMANAGER = "HOSTMANAGER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "roles_permissions",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;
}
