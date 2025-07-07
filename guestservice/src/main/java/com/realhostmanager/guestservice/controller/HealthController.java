package com.realhostmanager.guestservice.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guests")
public class HealthController {
    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("status", "pong");
    }
}
