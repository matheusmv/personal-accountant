package br.edu.ifce.matheus.api.controllers.responses;

import br.edu.ifce.matheus.domain.FinancialData;
import br.edu.ifce.matheus.domain.Wallet;
import br.edu.ifce.matheus.domain.enums.FinancialTransaction;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletInformationResponse {

    private String walletName;
    private Double profits;
    private Double expenses;

    public WalletInformationResponse(Wallet wallet) {
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
    }
}
