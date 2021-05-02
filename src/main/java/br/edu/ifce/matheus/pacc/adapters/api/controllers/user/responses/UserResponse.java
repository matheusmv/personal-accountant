package br.edu.ifce.matheus.pacc.adapters.api.controllers.user.responses;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import lombok.Value;

@Value
public class UserResponse {
    String id;

    public UserResponse(User user) {
        this.id = user.getId();
    }
}
