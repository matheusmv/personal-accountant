package br.edu.ifce.matheus.pacc.domain.services.utils;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidParameterException;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.UserValidator;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.UserValidationResult;
import br.edu.ifce.matheus.pacc.domain.services.utils.validations.ValidateUserCreation;
import org.springframework.stereotype.Component;

@Component
public final class ValidateUserCreationImpl implements ValidateUserCreation {
    @Override
    public void validate(User user) {
        UserValidationResult result = UserValidator.isNameValid()
                .and(UserValidator.isUsernameValid())
                .and(UserValidator.isEmailValid())
                .and(UserValidator.isPasswordValid())
                .apply(user);

        if (result != UserValidationResult.SUCCESS) {
            throw new InvalidParameterException(result.getResult());
        }
    }
}
