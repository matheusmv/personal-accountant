package br.edu.ifce.matheus.pacc.domain.exceptions;

public class FinancialDataNotFoundException extends RuntimeException {
    public FinancialDataNotFoundException(String message) {
        super(message);
    }
}
