package br.edu.ifce.matheus.usecase.impl.utils;

import br.edu.ifce.matheus.domain.Wallet;
import br.edu.ifce.matheus.usecase.exceptions.ValidationException;
import br.edu.ifce.matheus.usecase.impl.utils.combinators.WalletValidator;
import br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.WalletValidationResult;
import br.edu.ifce.matheus.usecase.impl.utils.validations.WalletValidations;
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
