package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.pacc.domain.exceptions.WalletExistsException;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driven.WalletRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewWallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateNewWalletService implements CreateNewWallet {

    private static final String USERNAME_NOT_VALID_MSG = "username %s not valid";

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Override
    public Wallet execute(Wallet wallet) {
        var userExists = userRepository.findUserByUsername(wallet.getOwnerUsername());

        if (userExists == null) {
            throw new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, wallet.getOwnerUsername()));
        }

        var walletExists = walletRepository.findByNameAndOwnerUsername(wallet.getName(), userExists.getUsername());

        if (walletExists != null) {
            throw new WalletExistsException("Wallet Already Exists");
        }

        wallet.setCreatedAt(LocalDateTime.now());

        return walletRepository.saveWallet(wallet);
    }
}
