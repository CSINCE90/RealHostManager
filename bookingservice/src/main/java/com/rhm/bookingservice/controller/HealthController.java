package com.rhm.bookingservice.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class HealthController {
    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("status", "pong");
    }
}