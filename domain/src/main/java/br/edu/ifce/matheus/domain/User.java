package br.edu.ifce.matheus.domain;

import br.edu.ifce.matheus.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;
    private String name;
    private String username;
    private String email;
    private String password;
    private UserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = false;
    private LocalDateTime createdAt;
    private ConfirmationToken confirmationToken;
}
