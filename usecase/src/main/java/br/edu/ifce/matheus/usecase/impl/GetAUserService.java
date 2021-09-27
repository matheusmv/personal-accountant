package br.edu.ifce.matheus.usecase.impl;

import br.edu.ifce.matheus.domain.User;
import br.edu.ifce.matheus.usecase.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.usecase.ports.driven.UserRepository;
import br.edu.ifce.matheus.usecase.ports.driver.GetAUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetAUserService implements GetAUser {

    private static final String USERNAME_NOT_VALID_MSG = "username %s not valid";

    private final UserRepository userRepository;

    @Override
    public User execute(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, username)));
    }
}
