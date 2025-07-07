package com.rhm.bookingservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {
    private Long id;
    private Long structureId;
    private Long userId;

    private String guestFullName;
    private String guestEmail;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private Integer numGuests;
    private String notes;

    private LocalDate createdAt;
}
