package br.edu.ifce.core.stories.user;

import br.edu.ifce.core.domain.User;
import br.edu.ifce.core.port.driven.UserRepositoryPort;
import br.edu.ifce.core.port.driver.CreateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
@Configuration
public class CreateUserUS implements CreateUserPort {

    private final UserRepositoryPort userRepository;

    @Override
    public User execute(User user) {
        if (userRepository.userEmailAlreadyExists(user.getEmail())) {
            throw new IllegalArgumentException("User Already Exists");
        }

        return userRepository.saveUser(user);
    }
}
