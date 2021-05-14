package br.edu.ifce.matheus.pacc.adapters.db;

import br.edu.ifce.matheus.pacc.adapters.db.mongo.WalletMongoRepository;
import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.ports.driven.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class WalletRepositoryImpl implements WalletRepository {

    @Autowired
    private WalletMongoRepository repository;

    @Override
    public Optional<Wallet> findWalletById(String id) {
        return repository.findById(id);
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return repository.save(wallet);
    }

    @Override
    public Optional<Wallet> findWalletByNameAndOwnerId(String walletName, String ownerId) {
        return repository.findWalletByNameAndOwnerId(walletName, ownerId);
    }
}
