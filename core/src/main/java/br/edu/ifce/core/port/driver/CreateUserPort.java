package br.edu.ifce.core.port.driver;

import br.edu.ifce.core.domain.User;

public interface CreateUserPort {
    User execute(User user);
}
