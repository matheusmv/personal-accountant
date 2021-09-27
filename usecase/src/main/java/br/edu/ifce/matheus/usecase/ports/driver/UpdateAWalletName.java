package br.edu.ifce.matheus.usecase.ports.driver;

import br.edu.ifce.matheus.domain.Wallet;

public interface UpdateAWalletName {
    Wallet execute(String ownerUsername, String walletName, String newWalletName);
}
