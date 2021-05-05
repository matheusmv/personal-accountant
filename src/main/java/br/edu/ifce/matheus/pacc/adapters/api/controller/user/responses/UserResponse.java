package br.edu.ifce.matheus.pacc.adapters.api.controller.user.responses;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import lombok.Data;

@Data
public class UserResponse {

    private String id;

    public UserResponse(User user) {
        this.id = user.getId();
    }
}
