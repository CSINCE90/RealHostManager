package com.realhostmanager.guestservice.service;

import com.realhostmanager.guestservice.dto.GuestDto;

import java.util.List;

public interface GuestService {
    GuestDto createGuest(GuestDto guestDto);
    List<GuestDto> getAllGuests();
    GuestDto getGuestById(Long id);
    GuestDto updateGuest(Long id, GuestDto guestDto);
    void deleteGuest(Long id);
}
