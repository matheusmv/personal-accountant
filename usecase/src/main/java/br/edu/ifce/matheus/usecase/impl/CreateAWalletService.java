package br.edu.ifce.matheus.usecase.impl;

import br.edu.ifce.matheus.domain.Wallet;
import br.edu.ifce.matheus.usecase.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.usecase.exceptions.WalletAlreadyExistsException;
import br.edu.ifce.matheus.usecase.impl.utils.validations.WalletValidations;
import br.edu.ifce.matheus.usecase.ports.driven.UserRepository;
import br.edu.ifce.matheus.usecase.ports.driven.WalletRepository;
import br.edu.ifce.matheus.usecase.ports.driver.CreateAWallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateAWalletService implements CreateAWallet {

    private static final String USERNAME_NOT_VALID_MSG = "username %s not valid";

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final WalletValidations walletValidations;

    @Override
    public Wallet execute(String ownerUsername, String walletName) {
        var userExists = userRepository.findByUsername(ownerUsername)
                .orElseThrow(() -> new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, ownerUsername)));

        boolean walletExists = walletRepository.findByNameAndOwnerId(walletName, userExists.getId()).isPresent();

        if (walletExists) {
            throw new WalletAlreadyExistsException("Wallet Already Exists");
        }

        var wallet = newWallet(walletName, userExists.getId());

        walletValidations.validate(wallet);

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
