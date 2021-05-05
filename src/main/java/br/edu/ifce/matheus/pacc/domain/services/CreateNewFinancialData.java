package br.edu.ifce.matheus.pacc.domain.services;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidFinancialDataAmountException;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidFinancialDataTypeException;
import br.edu.ifce.matheus.pacc.domain.ports.FinancialDataRepository;

import java.time.Instant;

public class CreateNewFinancialData {

    private FinancialDataRepository financialDataRepository;

    public CreateNewFinancialData(FinancialDataRepository financialDataRepository) {
        this.financialDataRepository = financialDataRepository;
    }

    public FinancialData execute(FinancialData financialData) {
        if (financialData.getType() == null) {
            throw new InvalidFinancialDataTypeException("The type of expense / profit cannot be null.");
        }

        if (financialData.getAmount() == null) {
            throw new InvalidFinancialDataAmountException("The amount of expense / profit cannot be null");
        }

        financialData.setCreatedAt(Instant.now());

        return financialDataRepository.saveFinancialData(financialData);
    }
}
