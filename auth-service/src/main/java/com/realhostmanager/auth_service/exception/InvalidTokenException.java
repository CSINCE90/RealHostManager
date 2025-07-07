package com.realhostmanager.auth_service.exception;

/**
 * Eccezione lanciata quando un token JWT è invalido o scaduto.
 */
public class InvalidTokenException extends CustomException {

    public InvalidTokenException(String message) {
        super(message);
    }
}
