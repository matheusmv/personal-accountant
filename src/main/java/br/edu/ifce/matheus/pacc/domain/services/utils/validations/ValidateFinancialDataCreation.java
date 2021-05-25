package br.edu.ifce.matheus.pacc.domain.services.utils.validations;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;

public interface ValidateFinancialDataCreation {
    void validate(FinancialData financialData);
}
