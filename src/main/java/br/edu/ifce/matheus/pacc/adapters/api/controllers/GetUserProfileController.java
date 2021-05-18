package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.UserProfileResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.GetUserProfile;
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
    private GetUserProfile getUserProfile;

    @GetMapping("/{username}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable String username) {
        var user = getUserProfile.execute(username);
        return new ResponseEntity<>(new UserProfileResponse(user), HttpStatus.OK);
    }
}
