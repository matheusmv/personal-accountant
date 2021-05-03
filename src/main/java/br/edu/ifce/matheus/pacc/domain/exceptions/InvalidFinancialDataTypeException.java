package br.edu.ifce.matheus.pacc.domain.exceptions;

import java.io.Serial;

public class InvalidFinancialDataTypeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidFinancialDataTypeException(String message) {
        super(message);
    }
}