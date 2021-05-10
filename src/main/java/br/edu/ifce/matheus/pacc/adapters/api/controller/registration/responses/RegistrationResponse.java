package br.edu.ifce.matheus.pacc.adapters.api.controller.registration.responses;

import lombok.Data;

@Data
public class RegistrationResponse {

    private String token;

    public RegistrationResponse(String token) {
        this.token = token;
    }
}
