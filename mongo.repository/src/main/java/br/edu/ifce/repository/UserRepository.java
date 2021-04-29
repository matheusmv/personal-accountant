package br.edu.ifce.repository;

import br.edu.ifce.core.domain.User;
import br.edu.ifce.core.port.driven.UserRepositoryPort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryPort {

}
