package br.edu.ifce.api.controller.responses;

import br.edu.ifce.core.domain.User;
import lombok.Value;

@Value
public class UserResponse {
    String id;

    public UserResponse(User user) {
        this.id = user.getId();
    }
}
