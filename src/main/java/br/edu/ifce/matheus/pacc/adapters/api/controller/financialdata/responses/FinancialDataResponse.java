package br.edu.ifce.matheus.pacc.adapters.api.controller.financialdata.responses;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import lombok.Data;

@Data
public class FinancialDataResponse {

    private String id;

    public FinancialDataResponse(FinancialData financialData) {
        this.id = financialData.getId();
    }
}
