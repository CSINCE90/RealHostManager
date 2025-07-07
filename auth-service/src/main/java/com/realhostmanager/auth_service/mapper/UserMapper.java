package com.realhostmanager.auth_service.mapper;

import com.realhostmanager.auth_service.dto.response.UserResponse;
import com.realhostmanager.auth_service.model.User;

/**
 * Mapper per convertire tra entità User e DTO UserResponse.
 */
public class UserMapper {

    public static UserResponse toDto(User user) {
        if (user == null) return null;

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .roleName(user.getRole().getName())
                .build();
    }
}
