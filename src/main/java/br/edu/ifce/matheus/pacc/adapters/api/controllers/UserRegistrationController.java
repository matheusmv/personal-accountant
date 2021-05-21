package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.ResendEmailConfirmationRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.UserRegistrationRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.EnableNewUserResponse;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.UserRegistrationResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.ConfirmANewUser;
import br.edu.ifce.matheus.pacc.domain.ports.driver.RegisterANewUser;
import br.edu.ifce.matheus.pacc.domain.ports.driver.ResendEmailConfirmation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserRegistrationController {

    final String confirmationLink = "http://localhost:8080/api/v1/users/confirmation?token=";

    private final RegisterANewUser registerANewUser;
    private final ConfirmANewUser confirmANewUser;
    private final ResendEmailConfirmation resendEmailConfirmation;

    @PostMapping("/registration")
    public ResponseEntity<UserRegistrationResponse> registerANewUser(@RequestBody UserRegistrationRequest request) {
        var message = registerANewUser.execute(request.toUser(), confirmationLink);
        return new ResponseEntity<>(new UserRegistrationResponse(message), HttpStatus.CREATED);
    }

    @GetMapping("/confirmation")
    public ResponseEntity<EnableNewUserResponse> confirmANewUser(@RequestParam("token") String token) {
        var message = confirmANewUser.execute(token);
        return new ResponseEntity<>(new EnableNewUserResponse(message), HttpStatus.OK);
    }

    @PostMapping("/resend-confirmation")
    public ResponseEntity<EnableNewUserResponse> resendEmailConfirmation(@RequestBody ResendEmailConfirmationRequest request) {
        var message = resendEmailConfirmation.execute(request.getEmail(), confirmationLink);
        return new ResponseEntity<>(new EnableNewUserResponse(message), HttpStatus.OK);
    }
}
