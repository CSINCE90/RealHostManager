package com.realhostmanager.auth_service.service.impl;

import com.realhostmanager.auth_service.dto.request.LoginRequest;
import com.realhostmanager.auth_service.dto.request.RegisterRequest;
import com.realhostmanager.auth_service.dto.response.AuthResponse;
import com.realhostmanager.auth_service.model.Role;
import com.realhostmanager.auth_service.model.User;
import com.realhostmanager.auth_service.repository.RoleRepository;
import com.realhostmanager.auth_service.repository.UserRepository;
import com.realhostmanager.auth_service.service.AuthService;
import com.realhostmanager.auth_service.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email già registrata");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Ruolo USER non trovato"));

        User user = User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(userRole)
                .build();

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser);

        return AuthResponse.builder()
                .token(token)
                .userId(savedUser.getId())
                .roleName(savedUser.getRole().getName())
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password non valida");
        }

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .roleName(user.getRole().getName())
                .build();
    }
}
