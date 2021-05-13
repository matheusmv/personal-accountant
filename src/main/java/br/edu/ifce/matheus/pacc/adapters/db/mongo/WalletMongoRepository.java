package br.edu.ifce.matheus.pacc.adapters.db.mongo;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface WalletMongoRepository extends MongoRepository<Wallet, String> {
    @Query(value = "{'name': ?0, 'ownerUsername': ?1}")
    Optional<Wallet> findByNameAndOwnerUsername(String walletName, String ownerUsername);
}
