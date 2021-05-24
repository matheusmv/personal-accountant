package br.edu.ifce.matheus.pacc.domain.services.utils.combinators;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.UserValidationResult;

import java.util.Optional;
import java.util.function.Function;

public interface UserRegistrationValidator extends Function<User, UserValidationResult> {
    static UserRegistrationValidator isNameValid() {
        return user -> {
            boolean nameNotNullAndNotEmpty = Optional.ofNullable(user)
                    .map(User::getName)
                    .filter(name -> !name.isBlank())
                    .isPresent();

            return nameNotNullAndNotEmpty ? UserValidationResult.SUCCESS : UserValidationResult.NAME_NOT_VALID;
        };
    }

    static UserRegistrationValidator isUsernameValid() {
        return user -> {
            boolean usernameNotNullAndNotEmpty = Optional.ofNullable(user)
                    .map(User::getUsername)
                    .filter(username -> !username.isBlank())
                    .isPresent();

            return usernameNotNullAndNotEmpty ? UserValidationResult.SUCCESS : UserValidationResult.USERNAME_NOT_VALID;
        };
    }

    static UserRegistrationValidator isEmailValid() {
        return user -> {
            boolean emailNotNullNotEmptyAndValidFormat = Optional.ofNullable(user)
                    .map(User::getEmail)
                    .filter(email -> !email.isBlank() && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
                    .isPresent();

            return emailNotNullNotEmptyAndValidFormat ? UserValidationResult.SUCCESS : UserValidationResult.EMAIL_NOT_VALID;
        };
    }

    static UserRegistrationValidator isPasswordValid() {
        return user -> {
            boolean passwordNotNullNotEmptyAndHasGoodLength = Optional.ofNullable(user)
                    .map(User::getPassword)
                    .filter(password -> !password.isBlank() && password.length() > 6)
                    .isPresent();

            return passwordNotNullNotEmptyAndHasGoodLength ? UserValidationResult.SUCCESS : UserValidationResult.PASSWORD_NOT_VALID;
        };
    }

    default UserRegistrationValidator and(UserRegistrationValidator validator) {
        return user -> {
            UserValidationResult result = this.apply(user);
            return result.equals(UserValidationResult.SUCCESS) ? validator.apply(user) : result;
        };
    }
}
