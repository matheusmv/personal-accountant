package br.edu.ifce.matheus.pacc.domain.exceptions;

import java.io.Serial;

public class InvalidFinancialDataAmountException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidFinancialDataAmountException(String message) {
        super(message);
    }
}
