package br.edu.ifce.matheus.pacc.adapters.email.exceptions;

public class EmailServiceException extends RuntimeException {
    public EmailServiceException(String message) {
        super(message);
    }
}
