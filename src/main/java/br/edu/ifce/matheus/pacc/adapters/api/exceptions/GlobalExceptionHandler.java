package br.edu.ifce.matheus.pacc.adapters.api.exceptions;

import br.edu.ifce.matheus.pacc.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<String> userExists(UserExistsException e) {
        return new ResponseEntity<String>("User Already Exists.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> invalidEmail(InvalidEmailException e) {
        return new ResponseEntity<String>("Email with invalid format.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> invalidPassword(InvalidPasswordException e) {
        return new ResponseEntity<String>("The password must be at least 6 characters long.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WalletExistsException.class)
    public ResponseEntity<String> invalidFinancialDataType(WalletExistsException e) {
        return new ResponseEntity<String>("Wallet Already Exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFinancialDataTypeException.class)
    public ResponseEntity<String> invalidFinancialDataType(InvalidFinancialDataTypeException e) {
        return new ResponseEntity<String>("The type of expense / profit cannot be null.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFinancialDataAmountException.class)
    public ResponseEntity<String> invalidFinancialDataAmount(InvalidFinancialDataAmountException e) {
        return new ResponseEntity<String>("The amount of expense / profit cannot be null", HttpStatus.BAD_REQUEST);
    }
}
