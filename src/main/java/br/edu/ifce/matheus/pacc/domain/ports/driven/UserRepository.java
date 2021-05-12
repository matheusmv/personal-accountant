package br.edu.ifce.matheus.pacc.domain.ports.driven;

import br.edu.ifce.matheus.pacc.domain.entities.User;

public interface UserRepository {
    User saveUser(User user);
    User findUserByEmail(String userEmail);
    User findUserByConfirmationToken(String confirmationToken);
}
