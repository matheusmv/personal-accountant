package br.edu.ifce.matheus.pacc.domain.ports.driver;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;

public interface GetUserWallet {
    Wallet execute(String ownerUsername, String walletName);
}