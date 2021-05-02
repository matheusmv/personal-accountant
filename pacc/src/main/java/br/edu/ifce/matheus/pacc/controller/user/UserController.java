package br.edu.ifce.matheus.pacc.controller.user;

import br.edu.ifce.matheus.pacc.controller.user.requests.UserRequest;
import br.edu.ifce.matheus.pacc.controller.user.responses.UserResponse;
import br.edu.ifce.matheus.pacc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        var user = userService.createUser(request.toUser());
        return new UserResponse(user);
    }
}
