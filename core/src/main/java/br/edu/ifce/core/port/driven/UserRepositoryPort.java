package br.edu.ifce.core.port.driven;

import br.edu.ifce.core.domain.User;

public interface UserRepositoryPort {
    User saveUser(User user);
    boolean userEmailAlreadyExists(String userEmail);
}
