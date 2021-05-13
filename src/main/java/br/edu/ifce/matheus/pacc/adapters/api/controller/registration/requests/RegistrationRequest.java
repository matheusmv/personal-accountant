package br.edu.ifce.matheus.pacc.adapters.api.controller.registration.requests;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import lombok.Data;

@Data
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    public User toUser() {
        var user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
