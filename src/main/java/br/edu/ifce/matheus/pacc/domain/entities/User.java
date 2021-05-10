package br.edu.ifce.matheus.pacc.domain.entities;

import br.edu.ifce.matheus.pacc.domain.entities.enums.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document
public class User {
    private String id;
    private String email;
    private String password;
    private UserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = false;
    private LocalDateTime createdAt;
    private ConfirmationToken confirmationToken;
}
