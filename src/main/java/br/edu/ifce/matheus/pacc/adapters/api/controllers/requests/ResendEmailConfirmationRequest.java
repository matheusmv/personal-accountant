package br.edu.ifce.matheus.pacc.adapters.api.controllers.requests;

import lombok.Data;

@Data
public class ResendEmailConfirmationRequest {
    private String email;
}
