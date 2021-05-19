package br.edu.ifce.matheus.pacc.domain.ports.driven;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;

public interface FinancialDataRepository {
    FinancialData save(FinancialData financialData);
}
