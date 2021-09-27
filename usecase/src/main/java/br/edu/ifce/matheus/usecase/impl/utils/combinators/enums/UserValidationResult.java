package br.edu.ifce.matheus.usecase.impl.utils.combinators.enums;

public enum UserValidationResult {
    SUCCESS("Success"),
    NAME_NOT_VALID("The name parameter cannot be null or empty."),
    USERNAME_NOT_VALID("The username parameter cannot be null or empty."),
    EMAIL_NOT_VALID("The email parameter cannot be null, empty and must have a valid format."),
    PASSWORD_NOT_VALID("The password parameter cannot be null, empty and must be longer than 6 characters.");

    private final String result;

    UserValidationResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
