package br.edu.ifce.matheus.pacc.domain.services.utils.combinators;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.FinancialDataValidationResult;

import java.util.Optional;
import java.util.function.Function;

public interface FinancialDataCreationValidator extends Function<FinancialData, FinancialDataValidationResult> {

    static FinancialDataCreationValidator isDescriptionValid() {
        return financialData -> {
            boolean descriptionNotNullAndNotEmpty = Optional.ofNullable(financialData)
                    .map(FinancialData::getDescription)
                    .filter(description -> !description.isBlank())
                    .isPresent();

            return descriptionNotNullAndNotEmpty ? FinancialDataValidationResult.SUCCESS : FinancialDataValidationResult.DESCRIPTION_NOT_VALID;
        };
    }

    static FinancialDataCreationValidator isAmountValid() {
        return financialData -> {
            boolean amountNotNullAndGreaterThanZero = Optional.ofNullable(financialData)
                    .map(FinancialData::getAmount)
                    .filter(amount -> amount.doubleValue() > 0)
                    .isPresent();

            return amountNotNullAndGreaterThanZero ? FinancialDataValidationResult.SUCCESS : FinancialDataValidationResult.AMOUNT_NOT_VALID;
        };
    }

    default FinancialDataCreationValidator and(FinancialDataCreationValidator validator) {
        return financialData -> {
            FinancialDataValidationResult result = this.apply(financialData);
            return result.equals(FinancialDataValidationResult.SUCCESS) ? validator.apply(financialData) : result;
        };
    }
}
