package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.entities.enums.FinancialTransaction;
import br.edu.ifce.matheus.pacc.domain.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.pacc.domain.exceptions.WalletNotFoundException;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driven.WalletRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.AddProfitsToAWallet;
import br.edu.ifce.matheus.pacc.domain.services.utils.validations.ValidateFinancialDataCreation;
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
    private final ValidateFinancialDataCreation validateFinancialDataCreation;

    @Override
    public FinancialData execute(String ownerUsername, String walletName, FinancialData financialData) {
        var user = userRepository.findByUsername(ownerUsername)
                .orElseThrow(() -> new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, ownerUsername)));

        var wallet = walletRepository.findByNameAndOwnerId(walletName, user.getId())
                .orElseThrow(() -> new WalletNotFoundException(String.format(INVALID_WALLET_NAME, walletName)));

        financialData.setIdentificationCode(generateIdentificationCode());
        financialData.setCreatedAt(LocalDateTime.now());
        financialData.setType(FinancialTransaction.PROFIT);

        validateFinancialDataCreation.validate(financialData);

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
