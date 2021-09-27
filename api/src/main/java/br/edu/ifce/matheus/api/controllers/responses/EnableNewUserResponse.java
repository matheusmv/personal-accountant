package br.edu.ifce.matheus.api.controllers.responses;

import lombok.Data;

@Data
public class EnableNewUserResponse {

    private String message;

    public EnableNewUserResponse(String message) {
        this.message = message;
    }
}
