package com.realhostmanager.guestservice.mapper;

import com.realhostmanager.guestservice.dto.GuestDto;
import com.realhostmanager.guestservice.model.Guest;

public class GuestMapper {

    public static GuestDto toDto(Guest guest) {
        GuestDto dto = new GuestDto();
        dto.setId(guest.getId());
        dto.setFirstName(guest.getFirstName());
        dto.setLastName(guest.getLastName());
        dto.setBirthDate(guest.getBirthDate());
        dto.setBirthPlace(guest.getBirthPlace());
        dto.setBirthProvince(guest.getBirthProvince());
        dto.setBirthCountry(guest.getBirthCountry());
        dto.setCitizenship(guest.getCitizenship());
        dto.setGender(guest.getGender());
        dto.setDocumentType(guest.getDocumentType());
        dto.setDocumentNumber(guest.getDocumentNumber());
        dto.setDocumentIssuePlace(guest.getDocumentIssuePlace());
        dto.setDocumentIssueCountry(guest.getDocumentIssueCountry());
        dto.setGuestType(guest.getGuestType());
        dto.setArrivalDate(guest.getArrivalDate());
        dto.setStayLength(guest.getStayLength());
        dto.setGroupLeader(guest.isGroupLeader());
        dto.setUserId(guest.getUserId());
        return dto;
    }

    public static Guest toEntity(GuestDto dto) {
        return Guest.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthDate(dto.getBirthDate())
                .birthPlace(dto.getBirthPlace())
                .birthProvince(dto.getBirthProvince())
                .birthCountry(dto.getBirthCountry())
                .citizenship(dto.getCitizenship())
                .gender(dto.getGender())
                .documentType(dto.getDocumentType())
                .documentNumber(dto.getDocumentNumber())
                .documentIssuePlace(dto.getDocumentIssuePlace())
                .documentIssueCountry(dto.getDocumentIssueCountry())
                .guestType(dto.getGuestType())
                .arrivalDate(dto.getArrivalDate())
                .stayLength(dto.getStayLength())
                .isGroupLeader(dto.isGroupLeader())
                .userId(dto.getUserId())
                .build();
    }
}
