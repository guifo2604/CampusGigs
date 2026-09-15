package com.fiap.campus_gigs.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record GigCreateRequest(
        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotBlank
        String category,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = true, message = "price must not be negative")
        BigDecimal price
) {
}