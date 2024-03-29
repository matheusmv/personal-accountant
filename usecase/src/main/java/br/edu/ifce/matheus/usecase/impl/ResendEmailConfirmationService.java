package br.edu.ifce.matheus.usecase.impl;

import br.edu.ifce.matheus.usecase.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.usecase.ports.driven.UserRepository;
import br.edu.ifce.matheus.usecase.ports.driver.ResendEmailConfirmation;
import br.edu.ifce.matheus.usecase.ports.driver.SendConfirmationEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ResendEmailConfirmationService implements ResendEmailConfirmation {

    private static final String EMAIL_NOT_VALID_MSG = "email %s not valid";

    private final UserRepository userRepository;
    private final SendConfirmationEmail sendConfirmationEmail;

    @Override
    public String execute(String userEmail, String confirmationLink) {
        var userExists = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(String.format(EMAIL_NOT_VALID_MSG, userEmail)));

        if (userExists.getEnabled()) {
            return "the account has already been confirmed";
        }

        var userToken = userExists.getConfirmationToken();

        if (userToken.hasExpired()) {
            userToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
            userExists.setConfirmationToken(userToken);
            userRepository.save(userExists);
        }

        sendConfirmationEmail.execute(userExists, confirmationLink);

        return "confirm your email";
    }
}
