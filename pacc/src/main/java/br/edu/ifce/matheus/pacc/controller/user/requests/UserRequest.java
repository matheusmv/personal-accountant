package br.edu.ifce.matheus.pacc.controller.user.requests;

import br.edu.ifce.matheus.pacc.domain.User;
import lombok.Value;

@Value
public class UserRequest {
    String email;
    String password;

    public User toUser() {
        var user = new User();

        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
