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
    public void execute(String walletName, String ownerUsername) {
        var userExists = userRepository.findUserByUsername(ownerUsername)
                .orElseThrow(() -> new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, ownerUsername)));

        boolean walletExists = walletRepository.findWalletByNameAndOwnerId(walletName, userExists.getId()).isPresent();

        if (walletExists) {
            throw new WalletExistsException("Wallet Already Exists");
        }

        var wallet = newWallet(walletName, userExists.getId());

        walletRepository.saveWallet(wallet);
    }

    private Wallet newWallet(String walletName, String ownerId) {
        return new Wallet(
                walletName,
                LocalDateTime.now(),
                ownerId
        );
    }
}
