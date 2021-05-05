package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.exceptions.WalletExistsException;
import br.edu.ifce.matheus.pacc.domain.ports.WalletRepository;

import java.time.Instant;

public class CreateNewWallet {

    private WalletRepository walletRepository;

    public CreateNewWallet(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet execute(Wallet wallet) {
        var walletDB = walletRepository.findByName(wallet.getName());

        if (walletDB != null) {
            throw new WalletExistsException("Wallet Already Exists");
        }

        wallet.setCreatedAt(Instant.now());

        return walletRepository.saveWallet(wallet);
    }
}
