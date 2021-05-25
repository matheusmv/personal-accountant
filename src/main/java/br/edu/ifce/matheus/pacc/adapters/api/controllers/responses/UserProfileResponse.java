package br.edu.ifce.matheus.pacc.adapters.api.controllers.responses;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserProfileResponse {

    private String id;
    private String name;
    private String username;
    private boolean enabled;
    private LocalDateTime createdAt;

    public UserProfileResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.enabled = user.getEnabled();
        this.createdAt = user.getCreatedAt();
    }
}
