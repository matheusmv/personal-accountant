package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidConfirmationTokenException;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.ConfirmANewUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfirmANewUserService implements ConfirmANewUser {

    private static final String TOKEN_NOT_VALID_MSG = "token %s not valid";
    private static final String EMAIL_ALREADY_CONFIRMED_MSG = "email already confirmed";
    private static final String TOKEN_EXPIRED_MSG = "token expired";

    private final UserRepository userRepository;

    @Override
    public String execute(String confirmationToken) {
        var user = userRepository.findByConfirmationToken(confirmationToken)
                .orElseThrow(() -> new InvalidConfirmationTokenException(String.format(TOKEN_NOT_VALID_MSG, confirmationToken)));

        if (user.getEnabled()) {
            throw new InvalidConfirmationTokenException(EMAIL_ALREADY_CONFIRMED_MSG);
        }

        var userToken = user.getConfirmationToken();

        if (userToken.hasExpired()) {
            throw new InvalidConfirmationTokenException(TOKEN_EXPIRED_MSG);
        }

        userToken.setConfirmedAt(LocalDateTime.now());

        user.setConfirmationToken(userToken);
        user.setEnabled(true);

        userRepository.save(user);

        return "confirmed";
    }
}
