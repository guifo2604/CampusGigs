package com.fiap.campus_gigs.dtos;

public record CepResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf,
        Boolean erro
) {
}