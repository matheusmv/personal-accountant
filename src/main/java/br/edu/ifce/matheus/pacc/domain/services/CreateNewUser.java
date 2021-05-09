package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidEmailException;
import br.edu.ifce.matheus.pacc.domain.exceptions.UserExistsException;
import br.edu.ifce.matheus.pacc.domain.ports.UserRepository;
import br.edu.ifce.matheus.pacc.domain.services.utils.EmailValidation;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CreateNewUser {

    private static final String EMAIL_NOT_VALID_MSG = "email %s not valid";

    private final UserRepository userRepository;

    public User execute(User user) {
        boolean isValidEmail = validateUserEmail(user.getEmail());

        if (!isValidEmail) {
            throw new InvalidEmailException(String.format(EMAIL_NOT_VALID_MSG, user.getEmail()));
        }

        var userDB = userRepository.findByEmail(user.getEmail());

        if (userDB != null) {
            throw new UserExistsException("User Already Exists.");
        }

        user.setCreatedAt(LocalDateTime.now());

        return userRepository.saveUser(user);
    }

    private boolean validateUserEmail(String email) {
        EmailValidation validation = new EmailValidation();
        return validation.test(email);
    }
}
