package br.edu.ifce.matheus.pacc.domain.ports.driver;

import br.edu.ifce.matheus.pacc.domain.entities.User;

public interface RegisterANewUser {
    String execute(User user, String ConfirmationLink);
}
