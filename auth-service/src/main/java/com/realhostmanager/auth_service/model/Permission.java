package com.realhostmanager.auth_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Entità che rappresenta un permesso (privilegio) assegnabile a uno o più ruoli.
 */
@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;
}
