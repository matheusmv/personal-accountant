package br.edu.ifce.matheus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
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
