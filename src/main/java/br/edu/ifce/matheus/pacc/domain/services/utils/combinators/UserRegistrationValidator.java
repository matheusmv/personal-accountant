package br.edu.ifce.matheus.pacc.domain.services.utils.combinators;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.UserValidationResult;

import java.util.function.Function;

public interface UserRegistrationValidator extends Function<User, UserValidationResult> {
    static UserRegistrationValidator isNameValid() {
        return user -> user.getName() != null &&
                !user.getName().isBlank() ?
                UserValidationResult.SUCCESS : UserValidationResult.NAME_NOT_VALID;
    }

    static UserRegistrationValidator isUsernameValid() {
        return user -> user.getUsername() != null &&
                !user.getUsername().isBlank() ?
                UserValidationResult.SUCCESS : UserValidationResult.USERNAME_NOT_VALID;
    }

    static UserRegistrationValidator isEmailValid() {
        return user -> user.getEmail() != null &&
                !user.getEmail().isBlank() &&
                user.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$") ?
                UserValidationResult.SUCCESS : UserValidationResult.EMAIL_NOT_VALID;
    }

    static UserRegistrationValidator isPasswordValid() {
        return user -> user.getPassword() != null &&
                !user.getPassword().isBlank() &&
                user.getPassword().length() > 6 ?
                UserValidationResult.SUCCESS : UserValidationResult.PASSWORD_NOT_VALID;
    }

    default UserRegistrationValidator and(UserRegistrationValidator validator) {
        return user -> {
            UserValidationResult result = this.apply(user);
            return result.equals(UserValidationResult.SUCCESS) ? validator.apply(user) : result;
        };
    }
}
