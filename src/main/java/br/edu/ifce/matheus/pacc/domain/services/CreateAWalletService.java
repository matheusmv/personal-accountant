package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.pacc.domain.exceptions.WalletExistsException;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driven.WalletRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateAWallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateAWalletService implements CreateAWallet {

    private static final String USERNAME_NOT_VALID_MSG = "username %s not valid";

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Override
    public Wallet execute(String ownerUsername, String walletName) {
        var userExists = userRepository.findByUsername(ownerUsername)
                .orElseThrow(() -> new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, ownerUsername)));

        boolean walletExists = walletRepository.findByNameAndOwnerId(walletName, userExists.getId()).isPresent();

        if (walletExists) {
            throw new WalletExistsException("Wallet Already Exists");
        }

        var wallet = newWallet(walletName, userExists.getId());

        walletRepository.save(wallet);

        return wallet;
    }

    private Wallet newWallet(String walletName, String ownerId) {
        return new Wallet(
                walletName,
                LocalDateTime.now(),
                ownerId
        );
    }
}
