package com.realhostmanager.auth_service.exception;

/**
 * Eccezione lanciata quando un utente non viene trovato.
 */
public class UserNotFoundException extends CustomException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
