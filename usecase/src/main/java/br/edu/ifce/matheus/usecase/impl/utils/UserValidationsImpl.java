package br.edu.ifce.matheus.usecase.impl.utils;

import br.edu.ifce.matheus.domain.User;
import br.edu.ifce.matheus.usecase.exceptions.ValidationException;
import br.edu.ifce.matheus.usecase.impl.utils.combinators.UserValidator;
import br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.UserValidationResult;
import br.edu.ifce.matheus.usecase.impl.utils.validations.UserValidations;
import org.springframework.stereotype.Component;

@Component
public final class UserValidationsImpl implements UserValidations {
    @Override
    public void validate(User user) {
        UserValidationResult result = UserValidator.nameIsValid()
                .and(UserValidator.usernameIsValid())
                .and(UserValidator.emailIsValid())
                .and(UserValidator.passwordIsValid())
                .apply(user);

        if (result != UserValidationResult.SUCCESS) {
            throw new ValidationException(result.getResult());
        }
    }
}
