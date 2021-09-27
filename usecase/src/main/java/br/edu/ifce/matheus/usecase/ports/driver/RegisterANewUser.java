package br.edu.ifce.matheus.usecase.ports.driver;

import br.edu.ifce.matheus.domain.User;

public interface RegisterANewUser {
    String execute(User user, String ConfirmationLink);
}
