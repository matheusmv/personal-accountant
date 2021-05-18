package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.pacc.domain.exceptions.WalletNotFoundException;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driven.WalletRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.GetUserWallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetUserWalletService implements GetUserWallet {

    private static final String USERNAME_NOT_VALID_MSG = "username %s not valid";
    private final static String INVALID_WALLET_NAME = "the %s wallet not exists";

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Override
    public Wallet execute(String ownerUsername, String walletName) {
        var user = userRepository.findUserByUsername(ownerUsername)
                .orElseThrow(() -> new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, ownerUsername)));

        return walletRepository.findWalletByNameAndOwnerId(walletName, user.getId())
                .orElseThrow(() -> new WalletNotFoundException(String.format(INVALID_WALLET_NAME, walletName)));
    }
}
