package br.edu.ifce.matheus.pacc.domain.entities;

import br.edu.ifce.matheus.pacc.domain.entities.enums.FinancialTransaction;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Document
public class FinancialData {
    private FinancialTransaction type;
    private String description;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
