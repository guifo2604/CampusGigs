package com.fiap.campus_gigs.exceptions;

public class GigNotFoundException extends RuntimeException {
    public GigNotFoundException(Long id) {
        super("Service not found: " + id);
    }
}