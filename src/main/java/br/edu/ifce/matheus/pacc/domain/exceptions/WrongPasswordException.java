package br.edu.ifce.matheus.pacc.domain.exceptions;

import java.io.Serial;

public class WrongPasswordException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public WrongPasswordException(String message) {
        super(message);
    }
}
