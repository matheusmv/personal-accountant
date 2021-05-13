package br.edu.ifce.matheus.pacc.adapters.api.controller.wallet.requests;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinancialDataRequest {

    private String description;
    private BigDecimal amount;

    public FinancialData toFinancialData() {
        var financialData = new FinancialData();

        financialData.setDescription(description);
        financialData.setAmount(amount);

        return financialData;
    }
}