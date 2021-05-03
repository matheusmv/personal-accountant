package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.exceptions.UserExistsException;
import br.edu.ifce.matheus.pacc.domain.ports.UserRepository;

import java.time.LocalDateTime;

public class CreateNewUser {

    private UserRepository userRepository;

    public CreateNewUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(User user) {
        var userDB = userRepository.findByEmail(user.getEmail());

        if (userDB != null) {
            throw new UserExistsException("User Already Exists.");
        }

        user.setCreatedAt(LocalDateTime.now());

        return userRepository.saveUser(user);
    }
}
