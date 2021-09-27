package br.edu.ifce.matheus.usecase.impl;

import br.edu.ifce.matheus.domain.Wallet;
import br.edu.ifce.matheus.usecase.ports.driven.UserRepository;
import br.edu.ifce.matheus.usecase.ports.driven.WalletRepository;
import br.edu.ifce.matheus.usecase.ports.driver.ListWalletsForAUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListWalletsForAUserService implements ListWalletsForAUser {

    private static final String USERNAME_NOT_VALID_MSG = "username %s not valid";

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Override
    public List<Wallet> execute(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USERNAME_NOT_VALID_MSG, username)));

        return walletRepository.findAllByOwnerId(user.getId());
    }
}
