package com.realhostmanager.auth_service.service;

import com.realhostmanager.auth_service.dto.request.RegisterRequest;
import com.realhostmanager.auth_service.model.User;

import java.util.List;

public interface UserService {

    boolean existsByEmail(String email);

    User createUser(RegisterRequest request);

    User createUser(User user);

    User getUserByEmail(String email);

    boolean passwordMatches(String rawPassword, String encodedPassword);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(String email, User request);

    void deleteUser(Long id);
}
