package br.edu.ifce.repository;

import br.edu.ifce.core.domain.User;
import br.edu.ifce.core.port.driven.UserRepositoryPort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, UserRepositoryPort {

}
