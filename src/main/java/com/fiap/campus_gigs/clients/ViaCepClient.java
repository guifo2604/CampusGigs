package com.fiap.campus_gigs.clients;

import com.fiap.campus_gigs.dtos.CepResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ViaCepClient {

    @GetExchange("/{cep}/json")
    CepResponse getByCep(@PathVariable String cep);
}