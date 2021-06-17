package br.edu.ifce.matheus.pacc.domain.services.utils;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.exceptions.ValidationException;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.WalletValidator;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.WalletValidationResult;
import br.edu.ifce.matheus.pacc.domain.services.utils.validations.ValidateWalletCreation;
import org.springframework.stereotype.Component;

@Component
public final class ValidateWalletCreationImpl implements ValidateWalletCreation {
    @Override
    public void validate(Wallet wallet) {
        WalletValidationResult result = WalletValidator.nameIsValid().apply(wallet);

        if (result != WalletValidationResult.SUCCESS) {
            throw new ValidationException(result.getResult());
        }
    }
}
