package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.UserRegistrationRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.UserRegistrationResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.RegisterNewUserAndSendConfirmationLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RegisterNewUserController {

    @Autowired
    private RegisterNewUserAndSendConfirmationLink registerNewUserAndSendConfirmationLink;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerNewUser(@RequestBody UserRegistrationRequest request) {
        String confirmationLink = "http://localhost:8080/api/v1/users/confirmation?token=";
        var message = registerNewUserAndSendConfirmationLink.execute(request.toUser(), confirmationLink);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserRegistrationResponse(message));
    }
}
