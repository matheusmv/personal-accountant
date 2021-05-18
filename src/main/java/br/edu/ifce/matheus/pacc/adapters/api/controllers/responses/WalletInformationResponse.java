package br.edu.ifce.matheus.pacc.adapters.api.controllers.responses;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import lombok.Data;

import java.util.List;

@Data
public class WalletInformationResponse {

    private String walletName;
    private List<FinancialData> financials;

    public WalletInformationResponse(Wallet wallet) {
        this.walletName = wallet.getName();
        this.financials = wallet.getFinancials();
    }
}
