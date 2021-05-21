package br.edu.ifce.matheus.pacc.domain.ports.driver;

public interface ResendEmailConfirmation {
    String execute(String userEmail, String confirmationLink);
}
