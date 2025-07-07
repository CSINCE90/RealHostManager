package com.realhostmanager.structure_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/structures")
public class HealthController {

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("status", "pong");
    }
}