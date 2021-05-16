package br.edu.ifce.matheus.pacc.domain.ports.driver;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;

public interface CreateNewProfitData {
    void execute(String ownerUsername, String walletName, FinancialData financialData);
}
