package br.edu.ifce.matheus.usecase.ports.driver;

import br.edu.ifce.matheus.domain.FinancialData;

public interface UpdateAFinancialData {
    FinancialData execute(String ownerUsername, String walletName, String identificationCode, FinancialData newFinancialData);
}
