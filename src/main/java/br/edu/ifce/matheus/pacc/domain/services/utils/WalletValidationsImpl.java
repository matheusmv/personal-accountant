package br.edu.ifce.matheus.pacc.domain.services.utils;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.exceptions.ValidationException;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.WalletValidator;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.WalletValidationResult;
import br.edu.ifce.matheus.pacc.domain.services.utils.validations.WalletValidations;
import org.springframework.stereotype.Component;

@Component
public final class WalletValidationsImpl implements WalletValidations {
    @Override
    public void validate(Wallet wallet) {
        WalletValidationResult result = WalletValidator.nameIsValid().apply(wallet);

        if (result != WalletValidationResult.SUCCESS) {
            throw new ValidationException(result.getResult());
        }
    }
}
