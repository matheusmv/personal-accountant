package br.edu.ifce.matheus.pacc.adapters.api.controllers.responses;

import lombok.Data;

@Data
public class UserProfileResponse {

    private String firstName;
    private String lastName;
    private String username;

    public UserProfileResponse(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }
}
