package br.edu.ifce.matheus.pacc.domain.ports.driver;

public interface RemoveAFinancialDataFromTheWallet {
    void execute(String ownerUsername, String walletName, String identificationCode);
}
