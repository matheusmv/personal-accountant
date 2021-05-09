package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.exceptions.WalletExistsException;
import br.edu.ifce.matheus.pacc.domain.ports.WalletRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CreateNewWallet {

    private final WalletRepository walletRepository;

    public Wallet execute(Wallet wallet) {
        var walletDB = walletRepository.findByName(wallet.getName());

        if (walletDB != null) {
            throw new WalletExistsException("Wallet Already Exists");
        }

        wallet.setCreatedAt(LocalDateTime.now());

        return walletRepository.saveWallet(wallet);
    }
}
