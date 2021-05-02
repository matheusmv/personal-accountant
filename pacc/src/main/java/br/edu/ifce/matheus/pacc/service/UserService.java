package br.edu.ifce.matheus.pacc.service;

import br.edu.ifce.matheus.pacc.domain.User;
import br.edu.ifce.matheus.pacc.repository.mongo.UserRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    public UserRepositoryMongo repository;

    public User createUser(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User Already Exists");
        }

        user.setCreatedAt(LocalDateTime.now());

        return repository.save(user);
    }
}
