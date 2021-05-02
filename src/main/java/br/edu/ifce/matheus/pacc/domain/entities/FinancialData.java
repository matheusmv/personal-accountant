package br.edu.ifce.matheus.pacc.domain.entities;

import br.edu.ifce.matheus.pacc.domain.entities.enums.FinancialTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class FinancialData {
    private String id;
    private FinancialTransaction type;
    private String description;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
