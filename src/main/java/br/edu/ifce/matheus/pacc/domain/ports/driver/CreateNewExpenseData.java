package br.edu.ifce.matheus.pacc.domain.ports.driver;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;

public interface CreateNewExpenseData {
    FinancialData execute(FinancialData financialData);
}
