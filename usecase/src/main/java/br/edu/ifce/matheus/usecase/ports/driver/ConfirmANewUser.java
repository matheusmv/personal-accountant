package br.edu.ifce.matheus.usecase.ports.driver;

public interface ConfirmANewUser {
    String execute(String confirmationToken);
}
