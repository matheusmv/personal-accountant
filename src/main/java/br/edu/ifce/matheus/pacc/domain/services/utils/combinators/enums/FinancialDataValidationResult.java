package br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums;

public enum FinancialDataValidationResult {
    SUCCESS("Success"),
    DESCRIPTION_NOT_VALID("The description parameter cannot be null or empty."),
    AMOUNT_NOT_VALID("The amount parameter cannot be less than or equal to zero and cannot be empty.");

    private final String result;

    FinancialDataValidationResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
