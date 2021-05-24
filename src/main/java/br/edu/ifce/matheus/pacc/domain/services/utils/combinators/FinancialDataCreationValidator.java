package br.edu.ifce.matheus.pacc.domain.services.utils.combinators;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.FinancialDataValidationResult;

import java.util.function.Function;

public interface FinancialDataCreationValidator extends Function<FinancialData, FinancialDataValidationResult> {

    static FinancialDataCreationValidator isDescriptionValid() {
        return financialData -> financialData.getDescription() != null &&
                !financialData.getDescription().isBlank() ?
                FinancialDataValidationResult.SUCCESS : FinancialDataValidationResult.DESCRIPTION_NOT_VALID;
    }

    static FinancialDataCreationValidator isAmountValid() {
        return financialData -> financialData.getAmount() != null &&
                financialData.getAmount().doubleValue() > 0 ?
                FinancialDataValidationResult.SUCCESS : FinancialDataValidationResult.AMOUNT_NOT_VALID;
    }

    default FinancialDataCreationValidator and(FinancialDataCreationValidator validator) {
        return financialData -> {
            FinancialDataValidationResult result = this.apply(financialData);
            return result.equals(FinancialDataValidationResult.SUCCESS) ? validator.apply(financialData) : result;
        };
    }
}
