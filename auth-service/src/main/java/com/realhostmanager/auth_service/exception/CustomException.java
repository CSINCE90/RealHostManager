package com.realhostmanager.auth_service.exception;

/**
 * Classe base per tutte le eccezioni custom nel servizio di autenticazione.
 */
public abstract class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
