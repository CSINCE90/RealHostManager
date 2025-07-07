package com.rhm.bookingservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long structureId;  // ID della struttura prenotata
    private Long userId;       // ID dell’host manager che ha creato la prenotazione

    private String guestFullName;
    private String guestEmail;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private Integer numGuests;
    private String notes;

    @Column(nullable = false)
    private LocalDate createdAt;
}