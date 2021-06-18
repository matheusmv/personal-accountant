package br.edu.ifce.matheus.pacc.domain.ports.driver;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;

public interface UpdateAFinancialData {
    FinancialData execute(String ownerUsername, String walletName, String identificationCode, FinancialData newFinancialData);
}
