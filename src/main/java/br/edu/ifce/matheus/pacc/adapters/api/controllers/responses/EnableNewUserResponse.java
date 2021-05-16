package br.edu.ifce.matheus.pacc.adapters.api.controllers.responses;

import lombok.Data;

@Data
public class EnableNewUserResponse {

    private String message;

    public EnableNewUserResponse(String message) {
        this.message = message;
    }
}
