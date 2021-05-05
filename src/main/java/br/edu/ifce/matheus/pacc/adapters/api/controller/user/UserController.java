package br.edu.ifce.matheus.pacc.adapters.api.controller.user;

import br.edu.ifce.matheus.pacc.adapters.api.controller.user.requests.UserRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controller.user.responses.UserResponse;
import br.edu.ifce.matheus.pacc.domain.ports.UserRepository;
import br.edu.ifce.matheus.pacc.domain.services.CreateNewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        var createNewUser = new CreateNewUser(userRepository);
        var savedUser = createNewUser.execute(request.toUser());

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new UserResponse(savedUser));
    }
}
