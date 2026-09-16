package com.fiap.campus_gigs.dtos;

import com.fiap.campus_gigs.dtos.UserResponse;

public record LoginResponse(String token, UserResponse user) {
}