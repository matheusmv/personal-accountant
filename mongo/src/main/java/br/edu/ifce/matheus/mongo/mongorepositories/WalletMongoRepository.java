package br.edu.ifce.matheus.mongo.mongorepositories;

import br.edu.ifce.matheus.domain.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletMongoRepository extends MongoRepository<Wallet, String> {

    @Query(value = "{'name': ?0, 'ownerId': ?1}")
    Optional<Wallet> findByNameAndOwnerId(String walletName, String ownerId);

    @Query(value = "{'ownerId': ?0}")
    List<Wallet> findAllByOwnerId(String ownerId);
}
