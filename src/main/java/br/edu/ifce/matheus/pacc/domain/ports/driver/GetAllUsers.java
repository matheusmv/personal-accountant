package br.edu.ifce.matheus.pacc.domain.ports.driver;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import org.springframework.data.domain.Page;

public interface GetAllUsers {
    Page<User> execute(int page, int size);
}
