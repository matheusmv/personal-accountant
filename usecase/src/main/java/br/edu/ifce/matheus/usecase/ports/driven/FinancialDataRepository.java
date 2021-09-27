package br.edu.ifce.matheus.usecase.ports.driven;

import br.edu.ifce.matheus.domain.FinancialData;

public interface FinancialDataRepository {
    FinancialData save(FinancialData financialData);
}
