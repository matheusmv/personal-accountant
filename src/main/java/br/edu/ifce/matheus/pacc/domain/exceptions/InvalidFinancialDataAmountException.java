package br.edu.ifce.matheus.pacc.domain.exceptions;

public class InvalidFinancialDataAmountException extends RuntimeException{
    public InvalidFinancialDataAmountException(String message) {
        super(message);
    }
}
