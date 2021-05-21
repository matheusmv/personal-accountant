package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.UserProfileResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.GetAUser;
import br.edu.ifce.matheus.pacc.domain.ports.driver.GetAllUsers;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserOperationsController {

    private final GetAllUsers getAllUsers;
    private final GetAUser getAUser;

    @GetMapping
    public ResponseEntity<List<UserProfileResponse>> getAllUsers() {
        // TODO: implement pagination and ordering
        var listOfUsers = getAllUsers.execute()
                .stream()
                .map(UserProfileResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponse> getAUser(@PathVariable String username) {
        var user = getAUser.execute(username);
        return new ResponseEntity<>(new UserProfileResponse(user), HttpStatus.OK);
    }
}
