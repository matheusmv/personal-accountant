package br.edu.ifce.matheus.usecase.impl;

import br.edu.ifce.matheus.domain.FinancialData;
import br.edu.ifce.matheus.domain.Wallet;
import br.edu.ifce.matheus.domain.enums.FinancialTransaction;
import br.edu.ifce.matheus.usecase.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.usecase.exceptions.WalletNotFoundException;
import br.edu.ifce.matheus.usecase.impl.utils.validations.FinancialDataValidations;
import br.edu.ifce.matheus.usecase.ports.driven.UserRepository;
import br.edu.ifce.matheus.usecase.ports.driven.WalletRepository;
import br.edu.ifce.matheus.usecase.ports.driver.AddProfitsToAWallet;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AddProfitsToAWalletService implements AddProfitsToAWallet {

    private static final String USERNAME_NOT_VALID_MSG = "username %s not valid";
    private static final String INVALID_WALLET_NAME = "the %s wallet not exists";

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final FinancialDataValidations financialDataValidations;

    @Override
    public FinancialData execute(String ownerUsername, String walletName, FinancialData financialData) {
        var user = userRepository.findByUsername(ownerUsername)
                .orElseThrow(() -> new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, ownerUsername)));

        var wallet = walletRepository.findByNameAndOwnerId(walletName, user.getId())
                .orElseThrow(() -> new WalletNotFoundException(String.format(INVALID_WALLET_NAME, walletName)));

        financialData.setIdentificationCode(generateIdentificationCode());
        financialData.setCreatedAt(LocalDateTime.now());
        financialData.setType(FinancialTransaction.PROFIT);

        financialDataValidations.validate(financialData);

        addNewProfitToWallet(wallet, financialData);

        walletRepository.save(wallet);

        return financialData;
    }

    private String generateIdentificationCode() {
        return RandomStringUtils.random(24, true, true);
    }

    private void addNewProfitToWallet(Wallet wallet, FinancialData financialData) {
        var walletFinancials = wallet.getFinancials();
        walletFinancials.add(financialData);
    }
}
