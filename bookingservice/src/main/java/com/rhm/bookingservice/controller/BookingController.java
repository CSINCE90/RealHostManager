package com.rhm.bookingservice.controller;

import com.rhm.bookingservice.dto.BookingDto;
import com.rhm.bookingservice.mapper.BookingMapper;
import com.rhm.bookingservice.model.Booking;
import com.rhm.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto dto) {
        Booking booking = BookingMapper.toEntity(dto);
        Booking saved = bookingService.createBooking(booking);
        return ResponseEntity.ok(BookingMapper.toDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        List<BookingDto> bookings = bookingService.getAllBookings().stream()
                .map(BookingMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(BookingMapper.toDto(booking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Long id, @RequestBody BookingDto dto) {
        Booking updated = bookingService.updateBooking(id, BookingMapper.toEntity(dto));
        return ResponseEntity.ok(BookingMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
