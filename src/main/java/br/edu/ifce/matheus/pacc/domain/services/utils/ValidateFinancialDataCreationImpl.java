package br.edu.ifce.matheus.pacc.domain.services.utils;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.exceptions.ValidationException;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.FinancialDataValidator;
import br.edu.ifce.matheus.pacc.domain.services.utils.combinators.enums.FinancialDataValidationResult;
import br.edu.ifce.matheus.pacc.domain.services.utils.validations.ValidateFinancialDataCreation;
import org.springframework.stereotype.Component;

@Component
public final class ValidateFinancialDataCreationImpl implements ValidateFinancialDataCreation {
    @Override
    public void validate(FinancialData financialData) {
        FinancialDataValidationResult result = FinancialDataValidator.descriptionIsValid()
                .and(FinancialDataValidator.amountIsValid())
                .apply(financialData);

        if (result != FinancialDataValidationResult.SUCCESS) {
            throw new ValidationException(result.getResult());
        }
    }
}
