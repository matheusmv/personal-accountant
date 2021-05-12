package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.entities.enums.FinancialTransaction;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidFinancialDataAmountException;
import br.edu.ifce.matheus.pacc.domain.ports.driven.FinancialDataRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewExpenseData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateNewExpenseDataService implements CreateNewExpenseData {

    private final FinancialDataRepository financialDataRepository;

    @Override
    public FinancialData execute(FinancialData financialData) {
        if (financialData.getAmount() == null) {
            throw new InvalidFinancialDataAmountException("The amount cannot be null");
        }

        financialData.setCreatedAt(LocalDateTime.now());
        financialData.setType(FinancialTransaction.EXPENSE);

        return financialDataRepository.saveFinancialData(financialData);
    }
}
