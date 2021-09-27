package br.edu.ifce.matheus.api.controllers.requests;

import br.edu.ifce.matheus.domain.FinancialData;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateFinancialDataRequest {
    private String description;
    private BigDecimal amount;

    public FinancialData toFinancialData() {
        var financialData = new FinancialData();

        financialData.setDescription(description);
        financialData.setAmount(amount);

        return financialData;
    }
}
