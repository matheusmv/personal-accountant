package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.exceptions.FinancialDataNotFoundException;
import br.edu.ifce.matheus.pacc.domain.exceptions.UserNotFoundException;
import br.edu.ifce.matheus.pacc.domain.exceptions.WalletNotFoundException;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driven.WalletRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.UpdateAFinancialData;
import br.edu.ifce.matheus.pacc.domain.services.utils.validations.FinancialDataValidations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UpdateAFinancialDataService implements UpdateAFinancialData {

    private static final String USERNAME_NOT_VALID_MSG = "username %s not valid";
    private static final String INVALID_WALLET_NAME = "the %s wallet not exists";
    private static final String INVALID_FINANCIAL_DATA_ID = "financial data was not found with the code %s";

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final FinancialDataValidations financialDataValidations;

    @Override
    public FinancialData execute(String ownerUsername, String walletName, String identificationCode, FinancialData newFinancialData) {
        var user = userRepository.findByUsername(ownerUsername)
                .orElseThrow(() -> new UserNotFoundException(String.format(USERNAME_NOT_VALID_MSG, ownerUsername)));

        var wallet = walletRepository.findByNameAndOwnerId(walletName, user.getId())
                .orElseThrow(() -> new WalletNotFoundException(String.format(INVALID_WALLET_NAME, walletName)));

        var financialData = findFinancialDataByIdentificationCode(wallet, identificationCode);

        updateTheFinancialData(financialData, newFinancialData);

        financialDataValidations.validate(financialData);

        walletRepository.save(wallet);

        return financialData;
    }

    private FinancialData findFinancialDataByIdentificationCode(Wallet wallet, String identificationCode) {
        return wallet.getFinancials()
                .stream()
                .filter(financialData -> financialData.getIdentificationCode().equals(identificationCode))
                .findFirst()
                .orElseThrow(() -> new FinancialDataNotFoundException(String.format(INVALID_FINANCIAL_DATA_ID, identificationCode)));
    }

    private void updateTheFinancialData(FinancialData financialData, FinancialData newFinancialData) {
        var description = Objects.nonNull(newFinancialData.getDescription());
        var amount = Objects.nonNull(newFinancialData.getAmount());

        if (description) {
            financialData.setDescription(newFinancialData.getDescription());
        }

        if (amount) {
            financialData.setAmount(newFinancialData.getAmount());
        }
    }
}
