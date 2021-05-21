package br.edu.ifce.matheus.pacc.adapters.api.controllers.responses;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import lombok.Data;

@Data
public class UserProfileResponse {

    private String name;
    private String username;

    public UserProfileResponse(User user) {
        this.name = user.getName();
        this.username = user.getUsername();
    }
}
