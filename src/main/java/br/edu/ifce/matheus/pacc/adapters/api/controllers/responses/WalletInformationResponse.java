package br.edu.ifce.matheus.pacc.adapters.api.controllers.responses;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import lombok.Data;

import java.util.List;

@Data
public class WalletInformationResponse {

    private String walletName;
    private List<FinancialData> financials;

    public WalletInformationResponse(String walletName, List<FinancialData> financials) {
        this.walletName = walletName;
        this.financials = financials;
    }
}
