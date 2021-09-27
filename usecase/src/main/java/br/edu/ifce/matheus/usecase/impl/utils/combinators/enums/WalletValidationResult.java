package br.edu.ifce.matheus.usecase.impl.utils.combinators.enums;

public enum WalletValidationResult {
    SUCCESS("Success"),
    NAME_NOT_VALID("The name parameter cannot be null or empty.");

    private final String result;

    WalletValidationResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
