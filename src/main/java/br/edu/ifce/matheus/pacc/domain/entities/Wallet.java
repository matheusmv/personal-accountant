package br.edu.ifce.matheus.pacc.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document
public class Wallet {

    private String id;
    private String name;
    private LocalDateTime createdAt;
    private String ownerId;
    private List<FinancialData> financials = new ArrayList<>();

    public Wallet(String name, LocalDateTime createdAt, String ownerId) {
        this.name = name;
        this.createdAt = createdAt;
        this.ownerId = ownerId;
    }
}
