package br.edu.ifce.matheus.pacc.domain.ports.driver;

public interface ConfirmANewUser {
    String execute(String confirmationToken);
}
