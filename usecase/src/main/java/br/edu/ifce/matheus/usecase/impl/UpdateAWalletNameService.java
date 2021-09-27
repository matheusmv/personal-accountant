package br.edu.ifce.matheus.usecase.impl;

import br.edu.ifce.matheus.domain.Wallet;
import br.edu.ifce.matheus.usecase.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.usecase.exceptions.WalletNotFoundException;
import br.edu.ifce.matheus.usecase.impl.utils.validations.WalletValidations;
import br.edu.ifce.matheus.usecase.ports.driven.UserRepository;
import br.edu.ifce.matheus.usecase.ports.driven.WalletRepository;
import br.edu.ifce.matheus.usecase.ports.driver.UpdateAWalletName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateAWalletNameService implements UpdateAWalletName {

    private static final String USERNAME_NOT_VALID_MSG = "username %s not valid";
    private static final String INVALID_WALLET_NAME = "the %s wallet not exists";

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final WalletValidations walletValidations;

    @Override
    public Wallet execute(String ownerUsername, String walletName, String newWalletName) {
        var user = userRepository.findByUsername(ownerUsername)
                .orElseThrow(() -> new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, ownerUsername)));

        var wallet = walletRepository.findByNameAndOwnerId(walletName, user.getId())
                .orElseThrow(() -> new WalletNotFoundException(String.format(INVALID_WALLET_NAME, walletName)));

        wallet.setName(newWalletName);

        walletValidations.validate(wallet);

        walletRepository.save(wallet);

        return wallet;
    }
}
