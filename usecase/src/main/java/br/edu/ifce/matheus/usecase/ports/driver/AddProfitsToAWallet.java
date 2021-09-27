package br.edu.ifce.matheus.usecase.ports.driver;

import br.edu.ifce.matheus.domain.FinancialData;

public interface AddProfitsToAWallet {
    FinancialData execute(String ownerUsername, String walletName, FinancialData financialData);
}
