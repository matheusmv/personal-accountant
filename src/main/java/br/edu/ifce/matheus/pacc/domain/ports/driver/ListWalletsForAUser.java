package br.edu.ifce.matheus.pacc.domain.ports.driver;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;

import java.util.List;

public interface ListWalletsForAUser {
    List<Wallet> execute(String username);
}
