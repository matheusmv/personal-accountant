package br.edu.ifce.matheus.usecase.impl.utils.combinators;

import br.edu.ifce.matheus.domain.FinancialData;
import br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.FinancialDataValidationResult;

import java.util.Optional;
import java.util.function.Function;

import static br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.FinancialDataValidationResult.AMOUNT_NOT_VALID;
import static br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.FinancialDataValidationResult.DESCRIPTION_NOT_VALID;
import static br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.FinancialDataValidationResult.SUCCESS;

public interface FinancialDataValidator extends Function<FinancialData, FinancialDataValidationResult> {

    static FinancialDataValidator descriptionIsValid() {
        return financialData -> {
            boolean descriptionNotNullAndNotEmpty = Optional.ofNullable(financialData)
                    .map(FinancialData::getDescription)
                    .filter(description -> !description.isBlank())
                    .isPresent();

            return descriptionNotNullAndNotEmpty ? SUCCESS : DESCRIPTION_NOT_VALID;
        };
    }

    static FinancialDataValidator amountIsValid() {
        return financialData -> {
            boolean amountNotNullAndGreaterThanZero = Optional.ofNullable(financialData)
                    .map(FinancialData::getAmount)
                    .filter(amount -> amount.doubleValue() > 0)
                    .isPresent();

            return amountNotNullAndGreaterThanZero ? SUCCESS : AMOUNT_NOT_VALID;
        };
    }

    default FinancialDataValidator and(FinancialDataValidator validator) {
        return financialData -> {
            FinancialDataValidationResult result = this.apply(financialData);
            return result.equals(SUCCESS) ? validator.apply(financialData) : result;
        };
    }
}
