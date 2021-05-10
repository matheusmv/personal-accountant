package br.edu.ifce.matheus.pacc.adapters.api.controller.registration.responses;

import lombok.Data;

@Data
public class RegistrationResponse {

    private String message;

    public RegistrationResponse(String message) {
        this.message = message;
    }
}
