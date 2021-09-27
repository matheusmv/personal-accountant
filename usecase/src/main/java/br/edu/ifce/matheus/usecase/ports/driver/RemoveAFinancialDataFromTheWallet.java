package br.edu.ifce.matheus.usecase.ports.driver;

public interface RemoveAFinancialDataFromTheWallet {
    void execute(String ownerUsername, String walletName, String identificationCode);
}
