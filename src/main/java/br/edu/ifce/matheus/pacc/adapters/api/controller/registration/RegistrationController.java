package br.edu.ifce.matheus.pacc.adapters.api.controller.registration;

import br.edu.ifce.matheus.pacc.adapters.api.controller.registration.requests.RegistrationRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controller.registration.responses.RegistrationResponse;
import br.edu.ifce.matheus.pacc.adapters.api.controller.registration.responses.RegistrationStatusResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.EnableUser;
import br.edu.ifce.matheus.pacc.domain.ports.driver.RegisterNewUserAndSendConfirmationLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private RegisterNewUserAndSendConfirmationLink registerNewUserAndSendConfirmationLink;
    @Autowired
    private EnableUser enableUser;

    @PostMapping
    public ResponseEntity<RegistrationResponse> userRegistration(@RequestBody RegistrationRequest request) {
        String confirmationLink = "http://localhost:8080/api/registration/confirm?token=";
        var message = registerNewUserAndSendConfirmationLink.execute(request.toUser(), confirmationLink);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse(message));
    }

    @GetMapping("/confirm")
    public ResponseEntity<RegistrationStatusResponse> confirmUserRegistration(@RequestParam("token") String token) {
        var message = enableUser.execute(token);
        return ResponseEntity.ok().body(new RegistrationStatusResponse(message));
    }
}
