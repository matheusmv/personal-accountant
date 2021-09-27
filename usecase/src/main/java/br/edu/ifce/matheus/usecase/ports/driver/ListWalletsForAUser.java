package br.edu.ifce.matheus.usecase.ports.driver;

import br.edu.ifce.matheus.domain.Wallet;

import java.util.List;

public interface ListWalletsForAUser {
    List<Wallet> execute(String username);
}
