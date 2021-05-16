package br.edu.ifce.matheus.pacc.domain.ports.driver;

public interface CreateNewWallet {
    void execute(String walletName, String ownerUsername);
}
