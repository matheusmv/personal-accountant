package br.edu.ifce.matheus.pacc.domain.services.utils.combinators;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.WalletValidationResult;

import java.util.function.Function;

public interface WalletCreationValidator extends Function<Wallet, WalletValidationResult> {
    static WalletCreationValidator isNameValid() {
        return wallet -> wallet.getName() != null &&
                !wallet.getName().isBlank() ?
                WalletValidationResult.SUCCESS : WalletValidationResult.NAME_NOT_VALID;
    }

    default WalletCreationValidator and(WalletCreationValidator validator) {
        return wallet -> {
            WalletValidationResult result = this.apply(wallet);
            return result.equals(WalletValidationResult.SUCCESS) ? validator.apply(wallet) : result;
        };
    }
}
