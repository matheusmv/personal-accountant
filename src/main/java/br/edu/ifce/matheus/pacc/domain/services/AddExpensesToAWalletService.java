package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.entities.enums.FinancialTransaction;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidFinancialDataAmountException;
import br.edu.ifce.matheus.pacc.domain.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.pacc.domain.exceptions.WalletNotFoundException;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driven.WalletRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.AddExpensesToAWallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AddExpensesToAWalletService implements AddExpensesToAWallet {

    private static final String USERNAME_NOT_VALID_MSG = "username %s not valid";
    private final static String INVALID_WALLET_NAME = "the %s wallet not exists";

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Override
    public FinancialData execute(String ownerUsername, String walletName, FinancialData financialData) {
        var userExists = userRepository.findByUsername(ownerUsername)
                .orElseThrow(() -> new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, ownerUsername)));

        var walletExists = walletRepository.findByNameAndOwnerId(walletName, userExists.getId())
                .orElseThrow(() -> new WalletNotFoundException(String.format(INVALID_WALLET_NAME, walletName)));

        if (financialData.getAmount() == null) {
            throw new InvalidFinancialDataAmountException("The amount cannot be null");
        }

        financialData.setCreatedAt(LocalDateTime.now());
        financialData.setType(FinancialTransaction.EXPENSE);

        var walletFinancials = walletExists.getFinancials();

        walletFinancials.add(financialData);

        walletRepository.save(walletExists);

        return financialData;
    }
}