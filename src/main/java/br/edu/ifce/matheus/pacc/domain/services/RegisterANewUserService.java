package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.ConfirmationToken;
import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.entities.enums.UserRole;
import br.edu.ifce.matheus.pacc.domain.exceptions.UserAlreadyExistsException;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.RegisterANewUser;
import br.edu.ifce.matheus.pacc.domain.ports.driver.SendConfirmationEmail;
import br.edu.ifce.matheus.pacc.domain.services.utils.validations.UserValidations;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegisterANewUserService implements RegisterANewUser {

    private static final String USERNAME_ALREADY_REGISTERED = "username already registered";
    private static final String EMAIL_ALREADY_REGISTERED = "email already registered";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SendConfirmationEmail sendConfirmationEmail;
    private final UserValidations userValidations;

    @Override
    public String execute(User user, String confirmationLink) {
        userValidations.validate(user);

        verifyIfUsernameAndEmailExists(user);

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setUserRole(UserRole.USER);
        user.setCreatedAt(LocalDateTime.now());

        String token = UUID.randomUUID().toString();

        user.setConfirmationToken(createConfirmationToken(token));

        userRepository.save(user);

        sendConfirmationEmail.execute(user, confirmationLink);

        return "confirm your email";
    }

    private void verifyIfUsernameAndEmailExists(User user) {
        boolean usernameExists = userRepository.findByUsername(user.getUsername()).isPresent();

        if (usernameExists) {
            throw new UserAlreadyExistsException(USERNAME_ALREADY_REGISTERED);
        }

        boolean emailExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (emailExists) {
            throw new UserAlreadyExistsException(EMAIL_ALREADY_REGISTERED);
        }
    }

    private ConfirmationToken createConfirmationToken(String token) {
        return new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15)
        );
    }
}
