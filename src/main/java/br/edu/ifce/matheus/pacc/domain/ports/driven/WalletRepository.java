package br.edu.ifce.matheus.pacc.domain.ports.driven;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletRepository {
    void save(Wallet wallet);

    Optional<Wallet> findByNameAndOwnerId(String name, String ownerId);

    List<Wallet> findAllByOwnerId(String ownerId);
}
