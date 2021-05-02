package br.edu.ifce.matheus.pacc.adapters.api.controller.financialdata.requests;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.entities.enums.FinancialTransaction;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class FinancialDataRequest {
    FinancialTransaction type;
    String description;
    BigDecimal amount;

    public FinancialData toFinancialData() {
        var financialData = new FinancialData();

        financialData.setType(type);
        financialData.setDescription(description);
        financialData.setAmount(amount);

        return financialData;
    }
}
