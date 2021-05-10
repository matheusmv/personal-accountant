package br.edu.ifce.matheus.pacc.adapters.api.controller.registration.responses;

import lombok.Data;

@Data
public class RegistrationStatusResponse {

    private String message;

    public RegistrationStatusResponse(String message) {
        this.message = message;
    }
}
