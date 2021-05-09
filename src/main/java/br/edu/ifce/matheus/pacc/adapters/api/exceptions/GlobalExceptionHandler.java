package br.edu.ifce.matheus.pacc.adapters.api.exceptions;

import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidEmailException;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidFinancialDataAmountException;
import br.edu.ifce.matheus.pacc.domain.exceptions.UserExistsException;
import br.edu.ifce.matheus.pacc.domain.exceptions.WalletExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<StandardError> userExists(UserExistsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "UserExistsException",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<StandardError> invalidEmail(InvalidEmailException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "InvalidEmailException",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(WalletExistsException.class)
    public ResponseEntity<StandardError> walletExists(WalletExistsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "WalletExistsException",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidFinancialDataAmountException.class)
    public ResponseEntity<StandardError> invalidFinancialDataAmount(InvalidFinancialDataAmountException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "InvalidFinancialDataAmountException",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
}
