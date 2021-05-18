package br.edu.ifce.matheus.pacc.adapters.api.controllers.responses;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import lombok.Data;

@Data
public class UserProfileResponse {

    private String firstName;
    private String lastName;
    private String username;

    public UserProfileResponse(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
    }
}
