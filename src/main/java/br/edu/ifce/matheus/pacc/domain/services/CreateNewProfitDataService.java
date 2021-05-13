package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.entities.enums.FinancialTransaction;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidFinancialDataAmountException;
import br.edu.ifce.matheus.pacc.domain.exceptions.WalletNotFoundException;
import br.edu.ifce.matheus.pacc.domain.ports.driven.WalletRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewProfitData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateNewProfitDataService implements CreateNewProfitData {

    private final static String INVALID_WALLET_ID = "the %s id is not valid";

    private final WalletRepository walletRepository;

    @Override
    public void execute(String walletId, FinancialData financialData) {
        var walletExists = walletRepository.findWalletById(walletId)
                .orElseThrow(() -> new WalletNotFoundException(String.format(INVALID_WALLET_ID, walletId)));

        if (financialData.getAmount() == null) {
            throw new InvalidFinancialDataAmountException("The amount cannot be null");
        }

        financialData.setCreatedAt(LocalDateTime.now());
        financialData.setType(FinancialTransaction.PROFIT);

        var walletFinancials = walletExists.getFinancials();

        walletFinancials.add(financialData);

        walletRepository.saveWallet(walletExists);
    }
}
