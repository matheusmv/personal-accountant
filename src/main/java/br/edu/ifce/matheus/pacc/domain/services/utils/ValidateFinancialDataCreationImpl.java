package br.edu.ifce.matheus.pacc.domain.services.utils;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidParameterException;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.FinancialDataCreationValidator;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.FinancialDataValidationResult;

public final class ValidateFinancialDataCreationImpl {
    public static void validate(FinancialData financialData) {
        FinancialDataValidationResult result = FinancialDataCreationValidator.isDescriptionValid()
                .and(FinancialDataCreationValidator.isAmountValid())
                .apply(financialData);

        if (result != FinancialDataValidationResult.SUCCESS) {
            throw new InvalidParameterException(result.getResult());
        }
    }
}
