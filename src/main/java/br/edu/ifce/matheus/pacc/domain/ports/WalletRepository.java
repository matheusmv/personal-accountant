package br.edu.ifce.matheus.pacc.domain.ports;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;

public interface WalletRepository {
    Wallet saveWallet(Wallet wallet);
    Wallet findByName(String name);
}
