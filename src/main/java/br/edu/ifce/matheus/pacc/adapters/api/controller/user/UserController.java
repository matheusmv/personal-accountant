package br.edu.ifce.matheus.pacc.adapters.api.controller.user;

import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
}
