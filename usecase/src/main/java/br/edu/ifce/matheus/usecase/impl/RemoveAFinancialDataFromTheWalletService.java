package br.edu.ifce.matheus.usecase.impl;

import br.edu.ifce.matheus.domain.Wallet;
import br.edu.ifce.matheus.usecase.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.usecase.exceptions.WalletNotFoundException;
import br.edu.ifce.matheus.usecase.ports.driven.UserRepository;
import br.edu.ifce.matheus.usecase.ports.driven.WalletRepository;
import br.edu.ifce.matheus.usecase.ports.driver.RemoveAFinancialDataFromTheWallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RemoveAFinancialDataFromTheWalletService implements RemoveAFinancialDataFromTheWallet {

    private static final String USERNAME_NOT_VALID_MSG = "username %s not valid";
    private static final String INVALID_WALLET_NAME = "the %s wallet not exists";

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Override
    public void execute(String ownerUsername, String walletName, String identificationCode) {
        var user = userRepository.findByUsername(ownerUsername)
                .orElseThrow(() -> new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, ownerUsername)));

        var wallet = walletRepository.findByNameAndOwnerId(walletName, user.getId())
                .orElseThrow(() -> new WalletNotFoundException(String.format(INVALID_WALLET_NAME, walletName)));

        removeFinancialDataByIdentificationCode(wallet, identificationCode);

        walletRepository.save(wallet);
    }

    private void removeFinancialDataByIdentificationCode(Wallet wallet, String identificationCode) {
        var modifiedFinancials = wallet.getFinancials()
                .stream()
                .filter(financialData -> !financialData.getIdentificationCode().equals(identificationCode))
                .collect(Collectors.toList());

        wallet.setFinancials(modifiedFinancials);
    }
}
