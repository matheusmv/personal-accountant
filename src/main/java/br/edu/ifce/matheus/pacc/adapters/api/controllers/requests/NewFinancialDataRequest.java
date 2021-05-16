package br.edu.ifce.matheus.pacc.adapters.api.controllers.requests;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewFinancialDataRequest {

    private String description;
    private BigDecimal amount;

    public FinancialData toFinancialData() {
        var financialData = new FinancialData();

        financialData.setDescription(description);
        financialData.setAmount(amount);

        return financialData;
    }
}
