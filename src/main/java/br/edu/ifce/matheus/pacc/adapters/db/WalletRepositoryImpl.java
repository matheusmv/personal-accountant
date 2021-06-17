package br.edu.ifce.matheus.pacc.adapters.db;

import br.edu.ifce.matheus.pacc.adapters.db.mongo.WalletMongoRepository;
import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.ports.driven.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WalletRepositoryImpl implements WalletRepository {

    @Autowired
    private WalletMongoRepository repository;

    @Override
    public void save(Wallet wallet) {
        repository.save(wallet);
    }

    @Override
    public Optional<Wallet> findByNameAndOwnerId(String walletName, String ownerId) {
        return repository.findByNameAndOwnerId(walletName, ownerId);
    }

    @Override
    public List<Wallet> findAllByOwnerId(String ownerId) {
        return repository.findAllByOwnerId(ownerId);
    }

    @Override
    public void delete(Wallet wallet) {
        repository.delete(wallet);
    }
}
