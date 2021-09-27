package br.edu.ifce.matheus.usecase.impl;

import br.edu.ifce.matheus.usecase.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.usecase.exceptions.WalletNotFoundException;
import br.edu.ifce.matheus.usecase.ports.driven.UserRepository;
import br.edu.ifce.matheus.usecase.ports.driven.WalletRepository;
import br.edu.ifce.matheus.usecase.ports.driver.DeleteAWallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAWalletService implements DeleteAWallet {

    private static final String USERNAME_NOT_VALID_MSG = "username %s not valid";
    private static final String INVALID_WALLET_NAME = "the %s wallet not exists";

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Override
    public void execute(String ownerUsername, String walletName) {
        var user = userRepository.findByUsername(ownerUsername)
                .orElseThrow(() -> new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, ownerUsername)));

        var wallet = walletRepository.findByNameAndOwnerId(walletName, user.getId())
                .orElseThrow(() -> new WalletNotFoundException(String.format(INVALID_WALLET_NAME, walletName)));

        walletRepository.delete(wallet);
    }
}
