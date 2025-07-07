package com.realhostmanager.structure_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "structures")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Structure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;
    private String city;
    private String country;
    private String zipCode;

    @Column(nullable = false)
    private Long userId; // id dell'utente proprietario
}
