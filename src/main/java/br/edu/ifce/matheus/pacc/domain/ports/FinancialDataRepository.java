package br.edu.ifce.matheus.pacc.domain.ports;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;

public interface FinancialDataRepository {
    FinancialData saveFinancialData(FinancialData financialData);
}
