package br.edu.ifce.matheus.pacc.adapters.api.controller.financialdata.responses;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import lombok.Value;

@Value
public class FinancialDataResponse {
    String id;

    public FinancialDataResponse(FinancialData financialData) {
        this.id = financialData.getId();
    }
}
