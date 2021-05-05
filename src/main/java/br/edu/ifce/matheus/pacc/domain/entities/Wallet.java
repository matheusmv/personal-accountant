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
public class Wallet {
    private String id;
    private String name;
    private Instant createdAt;
}
