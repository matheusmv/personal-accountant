package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.entities.enums.FinancialTransaction;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidFinancialDataAmountException;
import br.edu.ifce.matheus.pacc.domain.ports.FinancialDataRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CreateNewExpenseData {

    private final FinancialDataRepository financialDataRepository;

    public FinancialData execute(FinancialData financialData) {
        if (financialData.getAmount() == null) {
            throw new InvalidFinancialDataAmountException("The amount cannot be null");
        }

        financialData.setCreatedAt(LocalDateTime.now());
        financialData.setType(FinancialTransaction.EXPENSE);

        return financialDataRepository.saveFinancialData(financialData);
    }
}
