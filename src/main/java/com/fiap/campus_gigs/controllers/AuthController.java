package com.fiap.campus_gigs.controllers;

import com.fiap.campus_gigs.dtos.LoginRequest;
import com.fiap.campus_gigs.dto.LoginResponse;
import com.fiap.campus_gigs.dtos.RegisterRequest;
import com.fiap.campus_gigs.dtos.UserResponse;
import com.fiap.campus_gigs.services.AuthService;
import com.fiap.campus_gigs.services.JwtService;
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
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        var user = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        var user = authService.authenticate(request);
        var token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return ResponseEntity.ok(new LoginResponse(token, UserResponse.from(user)));
    }
}