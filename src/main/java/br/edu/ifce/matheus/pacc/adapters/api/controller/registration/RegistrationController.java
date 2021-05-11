package br.edu.ifce.matheus.pacc.adapters.api.controller.registration;

import br.edu.ifce.matheus.pacc.adapters.api.controller.registration.requests.RegistrationRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controller.registration.responses.RegistrationResponse;
import br.edu.ifce.matheus.pacc.adapters.api.controller.registration.responses.RegistrationStatusResponse;
import br.edu.ifce.matheus.pacc.domain.ports.EmailSender;
import br.edu.ifce.matheus.pacc.domain.ports.PasswordEncoder;
import br.edu.ifce.matheus.pacc.domain.ports.UserRepository;
import br.edu.ifce.matheus.pacc.domain.services.EnableUser;
import br.edu.ifce.matheus.pacc.domain.services.RegisterNewUser;
import br.edu.ifce.matheus.pacc.domain.services.SendEmailConfirmationLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<RegistrationResponse> userRegistration(@RequestBody RegistrationRequest request) {
        var registerNewUser = new RegisterNewUser(userRepository, passwordEncoder);
        var user = registerNewUser.execute(request.toUser());

        String confirmationLink = "http://localhost:8080/api/registration/confirm?token=";

        var sendEmailConfirmationLink = new SendEmailConfirmationLink(emailSender);
        var message = sendEmailConfirmationLink.execute(user, confirmationLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse(message));
    }

    @GetMapping("/confirm")
    public ResponseEntity<RegistrationStatusResponse> confirmUserRegistration(@RequestParam("token") String token) {
        var enableUser = new EnableUser(userRepository);
        var message = enableUser.execute(token);
        return ResponseEntity.ok().body(new RegistrationStatusResponse(message));
    }
}
