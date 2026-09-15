package com.fiap.campus_gigs.exceptions;

public class CepServiceUnavailableException extends RuntimeException {
    public CepServiceUnavailableException() {
        super("CEP lookup service is unavailable or timed out, try again later");
    }
}