package com.realhostmanager.auth_service.util;

/**
 * Classe di utilità contenente costanti riutilizzabili in tutto il progetto.
 * Serve a centralizzare valori statici (es. ruoli, header, messaggi) per evitare duplicazioni e refusi.
 */
public class Constants {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";

    public static final String DEFAULT_ERROR_MESSAGE = "Si è verificato un errore interno.";
}
