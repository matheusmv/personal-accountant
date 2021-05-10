package br.edu.ifce.matheus.pacc.adapters.api.controller.registration;

import br.edu.ifce.matheus.pacc.adapters.api.controller.registration.requests.RegistrationRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controller.registration.responses.RegistrationResponse;
import br.edu.ifce.matheus.pacc.adapters.api.controller.registration.responses.RegistrationStatusResponse;
import br.edu.ifce.matheus.pacc.domain.ports.UserRepository;
import br.edu.ifce.matheus.pacc.domain.services.EnableUser;
import br.edu.ifce.matheus.pacc.domain.services.RegisterNewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<RegistrationResponse> userRegistration(@RequestBody RegistrationRequest request) {
        var registerNewUser = new RegisterNewUser(userRepository);
        var token = registerNewUser.execute(request.toUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse(token));
    }

    @GetMapping("/confirm")
    public ResponseEntity<RegistrationStatusResponse> confirmUserRegistration(@RequestParam("token") String token) {
        var enableUser = new EnableUser(userRepository);
        var message = enableUser.execute(token);
        return ResponseEntity.ok().body(new RegistrationStatusResponse(message));
    }
}
