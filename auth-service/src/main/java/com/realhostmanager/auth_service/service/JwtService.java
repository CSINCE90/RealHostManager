package com.realhostmanager.auth_service.service;

import com.realhostmanager.auth_service.model.User;

public interface JwtService {

    String generateToken(User user);

    String extractUsername(String token);

    boolean validateToken(String token);

    String extractEmail(String token);
}
