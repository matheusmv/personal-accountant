package br.edu.ifce.matheus.usecase.impl.utils.combinators;

import br.edu.ifce.matheus.domain.User;
import br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.UserValidationResult;

import java.util.Optional;
import java.util.function.Function;

import static br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.UserValidationResult.EMAIL_NOT_VALID;
import static br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.UserValidationResult.NAME_NOT_VALID;
import static br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.UserValidationResult.PASSWORD_NOT_VALID;
import static br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.UserValidationResult.SUCCESS;
import static br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.UserValidationResult.USERNAME_NOT_VALID;

public interface UserValidator extends Function<User, UserValidationResult> {
    static UserValidator nameIsValid() {
        return user -> {
            boolean nameNotNullAndNotEmpty = Optional.ofNullable(user)
                    .map(User::getName)
                    .filter(name -> !name.isBlank())
                    .isPresent();

            return nameNotNullAndNotEmpty ? SUCCESS : NAME_NOT_VALID;
        };
    }

    static UserValidator usernameIsValid() {
        return user -> {
            boolean usernameNotNullAndNotEmpty = Optional.ofNullable(user)
                    .map(User::getUsername)
                    .filter(username -> !username.isBlank())
                    .isPresent();

            return usernameNotNullAndNotEmpty ? SUCCESS : USERNAME_NOT_VALID;
        };
    }

    static UserValidator emailIsValid() {
        return user -> {
            boolean emailNotNullNotEmptyAndValidFormat = Optional.ofNullable(user)
                    .map(User::getEmail)
                    .filter(email -> !email.isBlank() && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
                    .isPresent();

            return emailNotNullNotEmptyAndValidFormat ? SUCCESS : EMAIL_NOT_VALID;
        };
    }

    static UserValidator passwordIsValid() {
        return user -> {
            boolean passwordNotNullNotEmptyAndHasGoodLength = Optional.ofNullable(user)
                    .map(User::getPassword)
                    .filter(password -> !password.isBlank() && password.length() > 6)
                    .isPresent();

            return passwordNotNullNotEmptyAndHasGoodLength ? SUCCESS : PASSWORD_NOT_VALID;
        };
    }

    default UserValidator and(UserValidator validator) {
        return user -> {
            UserValidationResult result = this.apply(user);
            return result.equals(SUCCESS) ? validator.apply(user) : result;
        };
    }
}
