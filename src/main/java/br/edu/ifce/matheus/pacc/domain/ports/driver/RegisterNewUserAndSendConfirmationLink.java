package br.edu.ifce.matheus.pacc.domain.ports.driver;

import br.edu.ifce.matheus.pacc.domain.entities.User;

public interface RegisterNewUserAndSendConfirmationLink {
    String execute(User user, String ConfirmationLink);
}
