package br.edu.ifce.matheus.pacc.domain.ports.driven;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;

import java.util.Optional;

public interface WalletRepository {
    Optional<Wallet> findWalletById(String walletId);

    Wallet saveWallet(Wallet wallet);

    Optional<Wallet> findByNameAndOwnerUsername(String name, String ownerUsername);
}
