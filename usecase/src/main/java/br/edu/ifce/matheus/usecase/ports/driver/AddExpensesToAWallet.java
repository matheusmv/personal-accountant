package br.edu.ifce.matheus.usecase.ports.driver;

import br.edu.ifce.matheus.domain.FinancialData;

public interface AddExpensesToAWallet {
    FinancialData execute(String ownerUsername, String walletName, FinancialData financialData);
}
