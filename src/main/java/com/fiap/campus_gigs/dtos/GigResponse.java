package com.fiap.campus_gigs.dtos;

import com.fiap.campus_gigs.models.Gig;

import java.math.BigDecimal;

public record GigResponse(
        Long id,
        String title,
        String description,
        String category,
        BigDecimal price,
        String status,
        Long providerId,
        String providerName
) {
    public static GigResponse from(Gig gig) {
        return new GigResponse(
                gig.getId(),
                gig.getTitle(),
                gig.getDescription(),
                gig.getCategory(),
                gig.getPrice(),
                gig.getStatus().name(),
                gig.getProvider().getId(),
                gig.getProvider().getName()
        );
    }
}