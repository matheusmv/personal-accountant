package br.edu.ifce.matheus.pacc.domain.ports.driver;

public interface DeleteAWallet {
    void execute(String ownerUsername, String walletName);
}
