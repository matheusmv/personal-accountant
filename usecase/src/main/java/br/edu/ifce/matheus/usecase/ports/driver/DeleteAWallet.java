package br.edu.ifce.matheus.usecase.ports.driver;

public interface DeleteAWallet {
    void execute(String ownerUsername, String walletName);
}
