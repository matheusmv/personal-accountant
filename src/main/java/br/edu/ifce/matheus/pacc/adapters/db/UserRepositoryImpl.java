package br.edu.ifce.matheus.pacc.adapters.db;

import br.edu.ifce.matheus.pacc.adapters.db.mongo.UserMongoRepository;
import br.edu.ifce.matheus.pacc.domain.entities.User;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMongoRepository repository;

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public Optional<User> findUserByEmail(String userEmail) {
        return repository.findUserByEmail(userEmail);
    }

    @Override
    public Optional<User> findUserByConfirmationToken(String confirmationToken) {
        return repository.findUserByConfirmationToken(confirmationToken);
    }
}
