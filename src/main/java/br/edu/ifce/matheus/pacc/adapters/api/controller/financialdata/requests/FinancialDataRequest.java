package br.edu.ifce.matheus.pacc.adapters.api.controller.financialdata.requests;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.entities.enums.FinancialTransaction;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinancialDataRequest {

    private FinancialTransaction type;
    private String description;
    private BigDecimal amount;

    public FinancialData toFinancialData() {
        var financialData = new FinancialData();

        financialData.setType(type);
        financialData.setDescription(description);
        financialData.setAmount(amount);

        return financialData;
    }
}
