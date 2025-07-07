package com.realhostmanager.auth_service.mapper;

import com.realhostmanager.auth_service.model.Role;

/**
 * Mapper per convertire entità Role in tipi primitivi o DTO.
 */
public class RoleMapper {

    public static String toNameString(Role role) {
        return role != null ? role.getName() : null;
    }
}
