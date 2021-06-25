package br.edu.ifce.matheus.pacc.adapters.api.exceptions;

import br.edu.ifce.matheus.pacc.adapters.email.exceptions.EmailServiceException;
import br.edu.ifce.matheus.pacc.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private StandardError getStandardError(RuntimeException exception,
                                           HttpServletRequest request,
                                           HttpStatus status) {
        return StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<StandardError> userAlreadyExists(UserAlreadyExistsException exception,
                                                           HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> userNotFound(UserNotFoundException exception,
                                                      HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandardError> validationException(ValidationException exception,
                                                             HttpServletRequest request) {
        var status = HttpStatus.UNPROCESSABLE_ENTITY;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(WalletAlreadyExistsException.class)
    public ResponseEntity<StandardError> walletAlreadyExists(WalletAlreadyExistsException exception,
                                                             HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<StandardError> walletNotFound(WalletNotFoundException exception,
                                                        HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidConfirmationTokenException.class)
    public ResponseEntity<StandardError> invalidConfirmationToken(InvalidConfirmationTokenException exception,
                                                                  HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EmailServiceException.class)
    public ResponseEntity<StandardError> emailServiceError(EmailServiceException exception,
                                                           HttpServletRequest request) {
        var status = HttpStatus.SERVICE_UNAVAILABLE;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(FinancialDataNotFoundException.class)
    public ResponseEntity<StandardError> financialDataNotFound(FinancialDataNotFoundException exception,
                                                               HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var error = getStandardError(exception, request, status);
        return ResponseEntity.status(status).body(error);
    }
}
