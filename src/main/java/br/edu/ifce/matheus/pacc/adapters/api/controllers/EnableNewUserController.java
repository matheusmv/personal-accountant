package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.EnableNewUserResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.EnableUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EnableNewUserController {

    @Autowired
    private EnableUser enableUser;

    @GetMapping("/users/confirmation")
    public ResponseEntity<EnableNewUserResponse> enableNewUser(@RequestParam("token") String token) {
        var message = enableUser.execute(token);
        return ResponseEntity.ok().body(new EnableNewUserResponse(message));
    }
}
