package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidEmailException;
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

    private static final String EMAIL_NOT_VALID_MSG = "email %s not valid";

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Override
    public Wallet execute(Wallet wallet) {
        var userExists = userRepository.findUserByEmail(wallet.getOwnerEmail());

        if (userExists == null) {
            throw new InvalidEmailException(String.format(EMAIL_NOT_VALID_MSG, wallet.getOwnerEmail()));
        }

        var walletExists = walletRepository.findByNameAndOwnerEmail(wallet.getName(), userExists.getEmail());

        if (walletExists != null) {
            throw new WalletExistsException("Wallet Already Exists");
        }

        wallet.setCreatedAt(LocalDateTime.now());

        return walletRepository.saveWallet(wallet);
    }
}
