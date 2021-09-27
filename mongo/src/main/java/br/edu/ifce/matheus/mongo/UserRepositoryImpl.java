package br.edu.ifce.matheus.mongo;

import br.edu.ifce.matheus.domain.User;
import br.edu.ifce.matheus.mongo.mongorepositories.UserMongoRepository;
import br.edu.ifce.matheus.usecase.ports.driven.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMongoRepository repository;

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String userEmail) {
        return repository.findByEmail(userEmail);
    }

    @Override
    public Optional<User> findByConfirmationToken(String confirmationToken) {
        return repository.findByConfirmationToken(confirmationToken);
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
