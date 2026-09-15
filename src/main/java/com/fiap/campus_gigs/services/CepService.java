package com.fiap.campus_gigs.services;

import com.fiap.campus_gigs.clients.ViaCepClient;
import com.fiap.campus_gigs.dtos.CepResponse;
import com.fiap.campus_gigs.exceptions.CepNotFoundException;
import com.fiap.campus_gigs.exceptions.CepServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
@RequiredArgsConstructor
public class CepService {

    private final ViaCepClient viaCepClient;

    public CepResponse lookup(String cep) {
        CepResponse response;
        try {
            response = viaCepClient.getByCep(cep);
        } catch (RestClientException ex) {
            // Serviço externo fora do ar, lento ou timeout: não deixamos a
            // operação silenciosamente incompleta, respondemos 503 explicando.
            throw new CepServiceUnavailableException();
        }

        // ViaCEP responde 200 OK com {"erro": true} para CEP inexistente,
        // em vez de 404 — por isso o tratamento explícito aqui.
        if (response == null || Boolean.TRUE.equals(response.erro())) {
            throw new CepNotFoundException(cep);
        }

        return response;
    }
}