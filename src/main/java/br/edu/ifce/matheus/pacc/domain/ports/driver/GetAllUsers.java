package br.edu.ifce.matheus.pacc.domain.ports.driver;

import br.edu.ifce.matheus.pacc.domain.entities.User;

import java.util.List;

public interface GetAllUsers {
    List<User> execute();
}
