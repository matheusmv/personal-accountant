package br.edu.ifce.matheus.usecase.ports.driven;

import br.edu.ifce.matheus.domain.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletRepository {
    void save(Wallet wallet);

    Optional<Wallet> findByNameAndOwnerId(String name, String ownerId);

    List<Wallet> findAllByOwnerId(String ownerId);

    void delete(Wallet wallet);
}
