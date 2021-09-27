package br.edu.ifce.matheus.usecase.ports.driver;

public interface ResendEmailConfirmation {
    String execute(String userEmail, String confirmationLink);
}
