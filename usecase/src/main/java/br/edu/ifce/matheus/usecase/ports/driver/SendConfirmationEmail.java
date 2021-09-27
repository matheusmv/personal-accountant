package br.edu.ifce.matheus.usecase.ports.driver;

import br.edu.ifce.matheus.domain.User;

public interface SendConfirmationEmail {
    void execute(User user, String confirmationLink);
}
