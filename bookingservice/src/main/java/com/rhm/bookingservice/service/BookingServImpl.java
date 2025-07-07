package com.rhm.bookingservice.service;

import com.rhm.bookingservice.model.Booking;
import com.rhm.bookingservice.repo.BookingRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServImpl implements BookingService {

    private final BookingRepo bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        booking.setCreatedAt(java.time.LocalDate.now());
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
    }

    @Override
    public Booking updateBooking(Long id, Booking updatedBooking) {
        Booking existing = getBookingById(id);
        updatedBooking.setId(existing.getId());
        updatedBooking.setCreatedAt(existing.getCreatedAt());
        return bookingRepository.save(updatedBooking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
