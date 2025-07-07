package com.rhm.bookingservice.repo;

import com.rhm.bookingservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    
    // In futuro potrai aggiungere metodi come: findByUserId(), findByStructureId(), ecc.
}