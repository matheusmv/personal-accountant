package br.edu.ifce.matheus.pacc.domain.exceptions;

import java.io.Serial;

public class WalletExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public WalletExistsException(String message) {
        super(message);
    }
}
