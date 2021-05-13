package br.edu.ifce.matheus.pacc.domain.ports.driven;

import br.edu.ifce.matheus.pacc.domain.entities.User;

import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String userEmail);

    Optional<User> findUserByConfirmationToken(String confirmationToken);
}
