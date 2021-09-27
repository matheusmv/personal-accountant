package br.edu.ifce.matheus.usecase.ports.driven;

import br.edu.ifce.matheus.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String userEmail);

    Optional<User> findByConfirmationToken(String confirmationToken);

    Page<User> findAllUsers(Pageable pageable);
}
