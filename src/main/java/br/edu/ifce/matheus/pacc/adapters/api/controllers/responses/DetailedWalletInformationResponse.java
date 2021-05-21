package br.edu.ifce.matheus.pacc.adapters.api.controllers.responses;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.entities.enums.FinancialTransaction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DetailedWalletInformationResponse {

    private String walletName;
    private Double profits;
    private Double expenses;
    private List<FinancialData> financials;

    public DetailedWalletInformationResponse(Wallet wallet) {
        this.walletName = wallet.getName();
        this.profits = wallet.getFinancials()
                .stream()
                .filter(financialData -> financialData.getType().equals(FinancialTransaction.PROFIT))
                .map(FinancialData::getAmount)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        this.expenses = wallet.getFinancials()
                .stream()
                .filter(financialData -> financialData.getType().equals(FinancialTransaction.EXPENSE))
                .map(FinancialData::getAmount)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        this.financials = wallet.getFinancials();
    }
}
