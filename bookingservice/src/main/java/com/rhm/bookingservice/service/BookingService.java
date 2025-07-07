package com.rhm.bookingservice.service;

import com.rhm.bookingservice.model.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(Booking booking);

    List<Booking> getAllBookings();

    Booking getBookingById(Long id);

    Booking updateBooking(Long id, Booking updatedBooking);

    void deleteBooking(Long id);
}
