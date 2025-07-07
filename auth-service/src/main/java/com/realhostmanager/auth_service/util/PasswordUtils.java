package com.realhostmanager.auth_service.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Classe di utilità per la gestione delle password con cifratura e verifica.
 * 
 * Serve per:
 * - Centralizzare l'uso del PasswordEncoder.
 * - Offrire metodi statici comodi per encoding/verifica password.
 * - Evitare duplicazioni nei service.
 */
public class PasswordUtils {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
