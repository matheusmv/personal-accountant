package br.edu.ifce.matheus.usecase.impl.utils.combinators;

import br.edu.ifce.matheus.domain.Wallet;
import br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.WalletValidationResult;

import java.util.Optional;
import java.util.function.Function;

import static br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.WalletValidationResult.NAME_NOT_VALID;
import static br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.WalletValidationResult.SUCCESS;

public interface WalletValidator extends Function<Wallet, WalletValidationResult> {
    static WalletValidator nameIsValid() {
        return wallet -> {
            boolean walletNameNotNullAndNotEmpty = Optional.ofNullable(wallet)
                    .map(Wallet::getName)
                    .filter(name -> !name.isBlank())
                    .isPresent();

            return walletNameNotNullAndNotEmpty ? SUCCESS : NAME_NOT_VALID;
        };
    }

    default WalletValidator and(WalletValidator validator) {
        return wallet -> {
            WalletValidationResult result = this.apply(wallet);
            return result.equals(SUCCESS) ? validator.apply(wallet) : result;
        };
    }
}
