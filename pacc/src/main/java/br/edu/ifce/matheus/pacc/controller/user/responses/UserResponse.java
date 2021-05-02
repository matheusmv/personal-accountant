package br.edu.ifce.matheus.pacc.controller.user.responses;

import br.edu.ifce.matheus.pacc.domain.User;
import lombok.Value;

@Value
public class UserResponse {
    String id;

    public UserResponse(User user) {
        this.id = user.getId();
    }
}
