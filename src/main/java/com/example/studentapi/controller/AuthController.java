package com.example.studentapi.controller;

import com.example.studentapi.model.AuthRequest;
import com.example.studentapi.model.AuthResponse;
import com.example.studentapi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Hardcoded admin credentials
    private final String ADMIN_USERNAME = "admin";

    // Encoded password for: password
    private final String ADMIN_PASSWORD =
            "$2a$10$lf/xOCYdC4/3qEe5VHV1Ue75fuhH9RRULrhsVki8FUf7veaWXfNaS";

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        // Validate username
        if (!ADMIN_USERNAME.equals(request.getUsername())) {
            return ResponseEntity
                    .status(401)
                    .body("Invalid username or password");
        }

        // Validate password
        if (!passwordEncoder.matches(
                request.getPassword(),
                ADMIN_PASSWORD)) {

            return ResponseEntity
                    .status(401)
                    .body("Invalid username or password");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(request.getUsername());

        // Return token response
        return ResponseEntity.ok(new AuthResponse(token));
    }
}