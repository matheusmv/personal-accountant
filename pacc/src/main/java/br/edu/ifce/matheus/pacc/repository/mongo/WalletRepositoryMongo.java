package br.edu.ifce.matheus.pacc.repository.mongo;

import br.edu.ifce.matheus.pacc.domain.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepositoryMongo extends MongoRepository<Wallet, String> {
    @Query(value = "{name: ?0}", exists = true)
    boolean existsByName(String name);

    @Query(value = "{name: ?0}")
    Wallet findByName(String name);
}
