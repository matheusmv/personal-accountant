package br.edu.ifce.matheus.pacc.adapters.api.controllers.user;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.user.requests.UserRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.user.responses.UserResponse;
import br.edu.ifce.matheus.pacc.domain.ports.UserRepository;
import br.edu.ifce.matheus.pacc.domain.services.CreateNewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        CreateNewUser createNewUser = new CreateNewUser(userRepository);
        var savedUser = createNewUser.execute(request.toUser());
        return new UserResponse(savedUser);
    }
}
