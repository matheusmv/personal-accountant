package br.edu.ifce.matheus.pacc.domain.ports.driver;

import br.edu.ifce.matheus.pacc.domain.entities.User;

public interface SendConfirmationEmail {
    void execute(User user, String confirmationLink);
}
