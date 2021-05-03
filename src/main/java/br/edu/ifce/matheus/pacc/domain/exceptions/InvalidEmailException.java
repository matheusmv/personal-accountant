package br.edu.ifce.matheus.pacc.domain.exceptions;

import java.io.Serial;

public class InvalidEmailException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidEmailException(String message) {
        super(message);
    }
}