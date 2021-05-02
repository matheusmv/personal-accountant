package br.edu.ifce.matheus.pacc.adapters.db;

import br.edu.ifce.matheus.pacc.adapters.db.mongo.WalletMongoRepository;
import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.ports.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WalletRepositoryImpl implements WalletRepository {

    @Autowired
    private WalletMongoRepository repository;

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return repository.save(wallet);
    }

    @Override
    public Wallet findByName(String walletName) {
        return repository.findByName(walletName);
    }
}
