package br.edu.ifce.matheus.usecase.exceptions;

public class FinancialDataNotFoundException extends RuntimeException {
    public FinancialDataNotFoundException(String message) {
        super(message);
    }
}
