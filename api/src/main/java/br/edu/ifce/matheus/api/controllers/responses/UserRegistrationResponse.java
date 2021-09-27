package br.edu.ifce.matheus.api.controllers.responses;

import lombok.Data;

@Data
public class UserRegistrationResponse {

    private String message;

    public UserRegistrationResponse(String message) {
        this.message = message;
    }
}
