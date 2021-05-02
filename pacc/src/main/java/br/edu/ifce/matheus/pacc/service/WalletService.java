package br.edu.ifce.matheus.pacc.service;

import br.edu.ifce.matheus.pacc.domain.Wallet;
import br.edu.ifce.matheus.pacc.repository.mongo.WalletRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WalletService {

    @Autowired
    private WalletRepositoryMongo repository;

    public Wallet createWallet(Wallet wallet) {
        if (repository.existsByName(wallet.getName())) {
            throw new IllegalArgumentException("Wallet Already Exists");
        }

        wallet.setCreatedAt(LocalDateTime.now());

        return repository.save(wallet);
    }

    public Wallet findByName(String walletName) {
        return repository.findByName(walletName);
    }
}
