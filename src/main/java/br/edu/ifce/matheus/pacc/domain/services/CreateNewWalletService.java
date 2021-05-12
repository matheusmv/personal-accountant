package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.exceptions.WalletExistsException;
import br.edu.ifce.matheus.pacc.domain.ports.driven.WalletRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewWallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateNewWalletService implements CreateNewWallet {

    private final WalletRepository walletRepository;

    @Override
    public Wallet execute(Wallet wallet) {
        var walletDB = walletRepository.findByName(wallet.getName());

        if (walletDB != null) {
            throw new WalletExistsException("Wallet Already Exists");
        }

        wallet.setCreatedAt(LocalDateTime.now());

        return walletRepository.saveWallet(wallet);
    }
}
