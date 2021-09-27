package br.edu.ifce.matheus.api.controllers.requests;

import br.edu.ifce.matheus.domain.User;
import lombok.Data;

@Data
public class UserRegistrationRequest {

    private String name;
    private String username;
    private String email;
    private String password;

    public User toUser() {
        var user = new User();

        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
