package com.realhostmanager.auth_service.exception;

/**
 * Eccezione lanciata quando un'email è già registrata nel sistema.
 */
public class EmailAlreadyExistsException extends CustomException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
