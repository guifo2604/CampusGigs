package com.fiap.campus_gigs.controllers;

import com.fiap.campus_gigs.dtos.LoginRequest;
import com.fiap.campus_gigs.dtos.RegisterRequest;
import com.fiap.campus_gigs.dtos.UserResponse;
import com.fiap.campus_gigs.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        var user = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest request) {
        var user = authService.authenticate(request);
        return ResponseEntity.ok(UserResponse.from(user));
    }
}