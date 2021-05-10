package br.edu.ifce.matheus.pacc.adapters.db;

import br.edu.ifce.matheus.pacc.adapters.db.mongo.UserMongoRepository;
import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.ports.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMongoRepository repository;

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public User findUserByEmail(String userEmail) {
        return repository.findUserByEmail(userEmail);
    }

    @Override
    public User findUserByConfirmationToken(String confirmationToken) {
        return repository.findUserByConfirmationToken(confirmationToken);
    }
}
