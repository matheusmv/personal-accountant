package br.edu.ifce.matheus.pacc.domain.services.utils.combinators;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.FinancialDataValidationResult;

import java.util.Optional;
import java.util.function.Function;

import static br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.FinancialDataValidationResult.*;

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
