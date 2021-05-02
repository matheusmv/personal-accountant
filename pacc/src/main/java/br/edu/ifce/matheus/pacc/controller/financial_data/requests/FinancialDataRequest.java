package br.edu.ifce.matheus.pacc.controller.financial_data.requests;

import br.edu.ifce.matheus.pacc.domain.FinancialData;
import br.edu.ifce.matheus.pacc.domain.enums.FinancialTransaction;
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
