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
        UserValidationResult result = UserValidator.nameIsValid()
                .and(UserValidator.usernameIsValid())
                .and(UserValidator.emailIsValid())
                .and(UserValidator.passwordIsValid())
                .apply(user);

        if (result != UserValidationResult.SUCCESS) {
            throw new InvalidParameterException(result.getResult());
        }
    }
}
