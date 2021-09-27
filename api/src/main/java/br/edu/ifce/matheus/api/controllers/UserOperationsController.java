package br.edu.ifce.matheus.api.controllers;

import br.edu.ifce.matheus.api.controllers.responses.UserPageableResponse;
import br.edu.ifce.matheus.api.controllers.responses.UserProfileResponse;
import br.edu.ifce.matheus.usecase.ports.driver.GetAUser;
import br.edu.ifce.matheus.usecase.ports.driver.GetAllUsers;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserOperationsController {

    private final GetAllUsers getAllUsers;
    private final GetAUser getAUser;

    @GetMapping
    public ResponseEntity<List<UserPageableResponse>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "5") int size) {
        var pageOfUsers = getAllUsers.execute(page, size);
        var listOfUsers = Stream.ofNullable(pageOfUsers)
                .map(UserPageableResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponse> getAUser(@PathVariable String username) {
        var user = getAUser.execute(username);
        return new ResponseEntity<>(new UserProfileResponse(user), HttpStatus.OK);
    }
}
