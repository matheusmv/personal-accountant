package br.edu.ifce.matheus.pacc.domain.ports.driver;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;

import java.util.List;

public interface GetAllWalletProfits {
    List<FinancialData> execute(String username, String walletName);
}
