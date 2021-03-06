package br.edu.ifce.matheus.pacc.adapters.db.mongo;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<User, String> {
    @Query(value = "{'username': ?0}")
    Optional<User> findByUsername(String username);

    @Query(value = "{'email': ?0}")
    Optional<User> findByEmail(String email);

    @Query(value = "{'confirmationToken.token': ?0}")
    Optional<User> findByConfirmationToken(String confirmationToken);
}
