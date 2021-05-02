package br.edu.ifce.core.userstories;

import br.edu.ifce.core.domain.User;
import br.edu.ifce.core.port.driven.UserRepositoryPort;
import br.edu.ifce.core.port.driver.CreateUserPort;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.time.LocalDateTime;

@Named
@RequiredArgsConstructor
public class CreateUserUS implements CreateUserPort {

    private final UserRepositoryPort repository;

    @Override
    public User execute(User user) {
        if (repository.userEmailAlreadyExists(user.getEmail())) {
            throw new IllegalArgumentException("User Already Exists");
        }

        user.setCreatedAt(LocalDateTime.now());

        return repository.save(user);
    }
}
