package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.ConfirmationToken;
import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.entities.enums.UserRole;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidEmailException;
import br.edu.ifce.matheus.pacc.domain.exceptions.UserExistsException;
import br.edu.ifce.matheus.pacc.domain.ports.driven.PasswordEncoder;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.RegisterNewUserAndSendConfirmationLink;
import br.edu.ifce.matheus.pacc.domain.ports.driver.SendConfirmationEmail;
import br.edu.ifce.matheus.pacc.domain.services.utils.EmailValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RegisterNewUserAndSendConfirmationLinkService implements RegisterNewUserAndSendConfirmationLink {

    private static final String EMAIL_NOT_VALID_MSG = "email %s not valid";
    private static final String USERNAME_ALREADY_REGISTERED = "username already registered";
    private static final String EMAIL_ALREADY_REGISTERED = "email already registered";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SendConfirmationEmail sendConfirmationEmail;

    @Override
    public String execute(User user, String confirmationLink) {
        validateUserEmail(user.getEmail());

        verifyIfUsernameAndEmailExists(user);

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setUserRole(UserRole.USER);
        user.setCreatedAt(LocalDateTime.now());

        String token = UUID.randomUUID().toString();

        user.setConfirmationToken(createConfirmationToken(token));

        userRepository.saveUser(user);

        sendConfirmationEmail.execute(user, confirmationLink);

        return "confirm your email";
    }

    private void verifyIfUsernameAndEmailExists(User user) {
        var userExists = userRepository.findUserByEmail(user.getEmail());
        userExists.ifPresent(usr -> {
            if (usr.getUsername().equals(user.getUsername())) {
                throw new UserExistsException(USERNAME_ALREADY_REGISTERED);
            }
            if (usr.getEmail().equals(user.getEmail())) {
                throw new UserExistsException(EMAIL_ALREADY_REGISTERED);
            }
        });
    }

    private void validateUserEmail(String userEmail) {
        EmailValidation validation = new EmailValidation();

        boolean isValidEmail = validation.test(userEmail);

        if (!isValidEmail) {
            throw new InvalidEmailException(String.format(EMAIL_NOT_VALID_MSG, userEmail));
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
