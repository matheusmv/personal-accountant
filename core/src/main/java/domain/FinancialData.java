package domain;

import domain.enums.FinancialTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialData {
    private String id;
    private FinancialTransaction type;
    private String description;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
