package br.edu.ifce.matheus.usecase.ports.driver;

import br.edu.ifce.matheus.domain.User;

public interface GetAUser {
    User execute(String username);
}
