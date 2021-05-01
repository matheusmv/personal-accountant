package br.edu.ifce.api.controller;

import br.edu.ifce.api.controller.requests.UserRequest;
import br.edu.ifce.api.controller.responses.UserResponse;
import br.edu.ifce.core.port.driver.CreateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final CreateUserPort createUserPort;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        var user = createUserPort.execute(request.toUser());
        return new UserResponse(user);
    }

    @GetMapping
    public String hello() {
        return "Hello World";
    }
}
