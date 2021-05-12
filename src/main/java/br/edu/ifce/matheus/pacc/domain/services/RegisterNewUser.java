package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.ConfirmationToken;
import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.entities.enums.UserRole;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidEmailException;
import br.edu.ifce.matheus.pacc.domain.exceptions.UserExistsException;
import br.edu.ifce.matheus.pacc.domain.ports.PasswordEncoder;
import br.edu.ifce.matheus.pacc.domain.ports.UserRepository;
import br.edu.ifce.matheus.pacc.domain.services.utils.EmailValidation;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
public class RegisterNewUser {

    private static final String EMAIL_NOT_VALID_MSG = "email %s not valid";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User execute(User user) {
        validateUserEmail(user.getEmail());

        var userExists = userRepository.findUserByEmail(user.getEmail());

        if (userExists != null) {
            throw new UserExistsException("User Already Exists.");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setUserRole(UserRole.USER);
        user.setCreatedAt(LocalDateTime.now());

        String token = UUID.randomUUID().toString();

        user.setConfirmationToken(createConfirmationToken(token));

        return userRepository.saveUser(user);
    }

    private ConfirmationToken createConfirmationToken(String token) {
        return new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15)
        );
    }

    private void validateUserEmail(String userEmail) {
        EmailValidation validation = new EmailValidation();

        boolean isValidEmail = validation.test(userEmail);

        if (!isValidEmail) {
            throw new InvalidEmailException(String.format(EMAIL_NOT_VALID_MSG, userEmail));
        }
    }
}
