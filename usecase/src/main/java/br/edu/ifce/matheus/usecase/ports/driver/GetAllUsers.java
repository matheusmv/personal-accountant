package br.edu.ifce.matheus.usecase.ports.driver;

import br.edu.ifce.matheus.domain.User;
import org.springframework.data.domain.Page;

public interface GetAllUsers {
    Page<User> execute(int page, int size);
}
