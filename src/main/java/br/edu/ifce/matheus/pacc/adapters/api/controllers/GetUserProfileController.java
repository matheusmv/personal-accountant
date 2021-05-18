package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.UserProfileResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GetUserProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable String username) {
        var userExists = userRepository.findUserByUsername(username);

        if (userExists.isPresent()) {
            var user = userExists.get();
            var userProfile = new UserProfileResponse(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUsername()
            );

            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
