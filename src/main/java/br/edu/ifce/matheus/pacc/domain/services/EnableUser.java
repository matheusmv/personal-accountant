package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidConfirmationTokenException;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidEmailException;
import br.edu.ifce.matheus.pacc.domain.ports.UserRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class EnableUser {

    private static final String TOKEN_NOT_VALID_MSG = "token %s not valid";
    private static final String EMAIL_ALREADY_CONFIRMED_MSG = "email already confirmed";
    private static final String TOKEN_EXPIRED_MSG = "token expired";

    private final UserRepository userRepository;

    public String execute(String confirmationToken) {
        var userDB = userRepository.findUserByConfirmationToken(confirmationToken);

        if (userDB == null) {
            throw new InvalidConfirmationTokenException(String.format(TOKEN_NOT_VALID_MSG, confirmationToken));
        }

        var userToken = userDB.getConfirmationToken();

        if (userToken.getConfirmedAt() != null) {
            throw new InvalidEmailException(EMAIL_ALREADY_CONFIRMED_MSG);
        }

        LocalDateTime expiredAt = userToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new InvalidConfirmationTokenException(TOKEN_EXPIRED_MSG);
        }

        userToken.setConfirmedAt(LocalDateTime.now());

        userDB.setConfirmationToken(userToken);
        userDB.setEnabled(true);

        userRepository.saveUser(userDB);

        return "confirmed";
    }
}
