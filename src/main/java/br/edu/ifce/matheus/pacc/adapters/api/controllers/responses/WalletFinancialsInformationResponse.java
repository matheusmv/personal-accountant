package br.edu.ifce.matheus.pacc.adapters.api.controllers.responses;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import lombok.Data;

import java.util.List;

@Data
public class WalletFinancialsInformationResponse {

    private List<FinancialData> financials;

    public WalletFinancialsInformationResponse(List<FinancialData> financials) {
        this.financials = financials;
    }
}
