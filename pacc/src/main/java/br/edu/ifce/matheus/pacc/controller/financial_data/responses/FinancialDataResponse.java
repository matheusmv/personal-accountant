package br.edu.ifce.matheus.pacc.controller.financial_data.responses;

import br.edu.ifce.matheus.pacc.domain.FinancialData;
import lombok.Value;

@Value
public class FinancialDataResponse {
    String id;

    public FinancialDataResponse(FinancialData financialData) {
        this.id = financialData.getId();
    }
}
