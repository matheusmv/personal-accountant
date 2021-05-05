package br.edu.ifce.matheus.pacc.adapters.api.controller.user.requests;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import lombok.Data;

@Data
public class UserRequest {

    private String email;
    private String password;

    public User toUser() {
        var user = new User();

        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
