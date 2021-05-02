package br.edu.ifce.matheus.pacc.adapters.db.mongo;

import br.edu.ifce.matheus.pacc.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserMongoRepository extends MongoRepository<User, String> {
    @Query(value = "{email: ?0}")
    User findByEmail(String email);
}
