package com.fiap.campus_gigs.dto;

import com.fiap.campus_gigs.dtos.UserResponse;

public record LoginResponse(String token, UserResponse user) {
}