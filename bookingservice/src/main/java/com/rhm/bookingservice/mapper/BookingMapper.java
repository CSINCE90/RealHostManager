package com.rhm.bookingservice.mapper;

import com.rhm.bookingservice.dto.BookingDto;
import com.rhm.bookingservice.model.Booking;

public class BookingMapper {

    public static BookingDto toDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setStructureId(booking.getStructureId());
        dto.setUserId(booking.getUserId());
        dto.setGuestFullName(booking.getGuestFullName());
        dto.setGuestEmail(booking.getGuestEmail());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setNumGuests(booking.getNumGuests());
        dto.setNotes(booking.getNotes());
        dto.setCreatedAt(booking.getCreatedAt());
        return dto;
    }

    public static Booking toEntity(BookingDto dto) {
        return Booking.builder()
                .id(dto.getId())
                .structureId(dto.getStructureId())
                .userId(dto.getUserId())
                .guestFullName(dto.getGuestFullName())
                .guestEmail(dto.getGuestEmail())
                .checkInDate(dto.getCheckInDate())
                .checkOutDate(dto.getCheckOutDate())
                .numGuests(dto.getNumGuests())
                .notes(dto.getNotes())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
