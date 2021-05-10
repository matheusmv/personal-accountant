package br.edu.ifce.matheus.pacc.domain.exceptions;

public class EmailSenderException extends RuntimeException {
    public EmailSenderException(String message) {
        super(message);
    }
}
