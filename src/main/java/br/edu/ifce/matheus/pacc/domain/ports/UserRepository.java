package br.edu.ifce.matheus.pacc.domain.ports;

import br.edu.ifce.matheus.pacc.domain.entities.User;

public interface UserRepository {
    User saveUser(User user);
    User findByEmail(String userEmail);
}
