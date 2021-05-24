package br.edu.ifce.matheus.pacc.domain.services.utils;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidParameterException;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.WalletCreationValidator;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.WalletValidationResult;

public final class ValidateWalletCreationImpl {
    public static void validate(Wallet wallet) {
        WalletValidationResult result = WalletCreationValidator.isNameValid().apply(wallet);

        if (result != WalletValidationResult.SUCCESS) {
            throw new InvalidParameterException(result.name());
        }
    }
}
