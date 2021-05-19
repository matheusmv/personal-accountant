package br.edu.ifce.matheus.pacc.domain.ports.driver;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;

public interface UpdateWalletName {
    Wallet execute(String username, String walletName, String newWalletName);
}
