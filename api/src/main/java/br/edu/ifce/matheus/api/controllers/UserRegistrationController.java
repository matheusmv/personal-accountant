package br.edu.ifce.matheus.api.controllers;

import br.edu.ifce.matheus.api.controllers.requests.ResendEmailConfirmationRequest;
import br.edu.ifce.matheus.api.controllers.requests.UserRegistrationRequest;
import br.edu.ifce.matheus.api.controllers.responses.EnableNewUserResponse;
import br.edu.ifce.matheus.api.controllers.responses.UserRegistrationResponse;
import br.edu.ifce.matheus.usecase.ports.driver.ConfirmANewUser;
import br.edu.ifce.matheus.usecase.ports.driver.RegisterANewUser;
import br.edu.ifce.matheus.usecase.ports.driver.ResendEmailConfirmation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserRegistrationController {

    private static final String CONFIRMATION_LINK = "http://localhost:8080/api/v1/users/confirmation?token=";

    private final RegisterANewUser registerANewUser;
    private final ConfirmANewUser confirmANewUser;
    private final ResendEmailConfirmation resendEmailConfirmation;

    @PostMapping("/registration")
    public ResponseEntity<UserRegistrationResponse> registerANewUser(@RequestBody UserRegistrationRequest request) {
        var message = registerANewUser.execute(request.toUser(), CONFIRMATION_LINK);
        return new ResponseEntity<>(new UserRegistrationResponse(message), HttpStatus.CREATED);
    }

    @GetMapping("/confirmation")
    public ResponseEntity<EnableNewUserResponse> confirmANewUser(@RequestParam("token") String token) {
        var message = confirmANewUser.execute(token);
        return new ResponseEntity<>(new EnableNewUserResponse(message), HttpStatus.OK);
    }

    @PostMapping("/resend-confirmation")
    public ResponseEntity<EnableNewUserResponse> resendEmailConfirmation(@RequestBody ResendEmailConfirmationRequest request) {
        var message = resendEmailConfirmation.execute(request.getEmail(), CONFIRMATION_LINK);
        return new ResponseEntity<>(new EnableNewUserResponse(message), HttpStatus.OK);
    }
}
