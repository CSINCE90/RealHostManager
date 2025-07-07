package com.realhostmanager.guestservice.service;

import com.realhostmanager.guestservice.dto.GuestDto;
import com.realhostmanager.guestservice.mapper.GuestMapper;
import com.realhostmanager.guestservice.model.Guest;
import com.realhostmanager.guestservice.repo.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    @Override
    public GuestDto createGuest(GuestDto guestDto) {
        Guest guest = GuestMapper.toEntity(guestDto);
        return GuestMapper.toDto(guestRepository.save(guest));
    }

    @Override
    public List<GuestDto> getAllGuests() {
        return guestRepository.findAll().stream()
                .map(GuestMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GuestDto getGuestById(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ospite non trovato con ID: " + id));
        return GuestMapper.toDto(guest);
    }

    @Override
    public GuestDto updateGuest(Long id, GuestDto guestDto) {
        Guest existing = guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ospite non trovato con ID: " + id));
        Guest updated = GuestMapper.toEntity(guestDto);
        updated.setId(existing.getId());
        return GuestMapper.toDto(guestRepository.save(updated));
    }

    @Override
    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }
}
