package br.edu.ifce.matheus.mongo.mongorepositories;

import br.edu.ifce.matheus.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {

    @Query(value = "{'username': ?0}")
    Optional<User> findByUsername(String username);

    @Query(value = "{'email': ?0}")
    Optional<User> findByEmail(String email);

    @Query(value = "{'confirmationToken.token': ?0}")
    Optional<User> findByConfirmationToken(String confirmationToken);
}
