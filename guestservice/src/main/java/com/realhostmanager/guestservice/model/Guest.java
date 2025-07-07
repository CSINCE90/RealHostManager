package com.realhostmanager.guestservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "guests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private LocalDate birthDate;
    private String birthPlace;
    private String birthProvince;
    private String birthCountry;

    private String citizenship;
    private String gender;

    private String documentType;
    private String documentNumber;
    private String documentIssuePlace;
    private String documentIssueCountry;

    private String guestType;

    private LocalDate arrivalDate;
    private Integer stayLength;

    private boolean isGroupLeader;

    private Long userId;
}
