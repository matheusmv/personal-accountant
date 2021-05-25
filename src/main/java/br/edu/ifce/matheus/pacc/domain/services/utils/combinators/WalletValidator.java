package br.edu.ifce.matheus.pacc.domain.services.utils.combinators;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.WalletValidationResult;

import java.util.Optional;
import java.util.function.Function;

public interface WalletValidator extends Function<Wallet, WalletValidationResult> {
    static WalletValidator isNameValid() {
        return wallet -> {
            boolean walletNameNotNullAndNotEmpty = Optional.ofNullable(wallet)
                    .map(Wallet::getName)
                    .filter(name -> !name.isBlank())
                    .isPresent();

            return walletNameNotNullAndNotEmpty ? WalletValidationResult.SUCCESS : WalletValidationResult.NAME_NOT_VALID;
        };
    }

    default WalletValidator and(WalletValidator validator) {
        return wallet -> {
            WalletValidationResult result = this.apply(wallet);
            return result.equals(WalletValidationResult.SUCCESS) ? validator.apply(wallet) : result;
        };
    }
}
