package br.edu.ifce.matheus.pacc.repository.mongo;

import br.edu.ifce.matheus.pacc.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryMongo extends MongoRepository<User, String> {
    @Query(value = "{email: ?0}", exists = true)
    boolean existsByEmail(String email);
}
