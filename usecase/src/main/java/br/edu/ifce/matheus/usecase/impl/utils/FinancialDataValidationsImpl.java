package br.edu.ifce.matheus.usecase.impl.utils;

import br.edu.ifce.matheus.domain.FinancialData;
import br.edu.ifce.matheus.usecase.exceptions.ValidationException;
import br.edu.ifce.matheus.usecase.impl.utils.combinators.FinancialDataValidator;
import br.edu.ifce.matheus.usecase.impl.utils.combinators.enums.FinancialDataValidationResult;
import br.edu.ifce.matheus.usecase.impl.utils.validations.FinancialDataValidations;
import org.springframework.stereotype.Component;

@Component
public final class FinancialDataValidationsImpl implements FinancialDataValidations {
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
