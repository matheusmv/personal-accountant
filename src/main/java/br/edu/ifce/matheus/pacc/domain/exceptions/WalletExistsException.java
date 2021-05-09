package br.edu.ifce.matheus.pacc.domain.exceptions;

public class WalletExistsException extends RuntimeException {
    public WalletExistsException(String message) {
        super(message);
    }
}
