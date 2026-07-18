package com.serviceflow.controller;

import com.serviceflow.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (username == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "username and password are required"));
        }

        if (!authService.isValidCredentials(username, password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "invalid credentials"));
        }

        return ResponseEntity.ok(Map.of(
                "token", authService.createToken(username),
                "user", Map.of("username", username, "roles", java.util.List.of("ADMIN"))
        ));
    }
}
