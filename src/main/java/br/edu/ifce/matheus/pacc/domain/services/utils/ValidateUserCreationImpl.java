package br.edu.ifce.matheus.pacc.domain.services.utils;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidParameterException;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.UserCreationValidator;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.UserValidationResult;

public final class ValidateUserCreationImpl {
    public static void validate(User user) {
        UserValidationResult result = UserCreationValidator.isNameValid()
                .and(UserCreationValidator.isUsernameValid())
                .and(UserCreationValidator.isEmailValid())
                .and(UserCreationValidator.isPasswordValid())
                .apply(user);

        if (result != UserValidationResult.SUCCESS) {
            throw new InvalidParameterException(result.getResult());
        }
    }
}
