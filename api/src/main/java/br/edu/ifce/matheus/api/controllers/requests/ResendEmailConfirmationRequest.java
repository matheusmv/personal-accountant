package br.edu.ifce.matheus.api.controllers.requests;

import lombok.Data;

@Data
public class ResendEmailConfirmationRequest {
    private String email;
}
