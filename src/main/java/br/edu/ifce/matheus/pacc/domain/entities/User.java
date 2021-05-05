package br.edu.ifce.matheus.pacc.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    private String id;
    private String email;
    private String password;
    private Instant createdAt;
}
