package br.edu.ifce.matheus.pacc.service;

import br.edu.ifce.matheus.pacc.domain.FinancialData;
import br.edu.ifce.matheus.pacc.repository.mongo.FinancialDataRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FinancialDataService {

    @Autowired
    private FinancialDataRepositoryMongo repository;

    public FinancialData createFinancialData(FinancialData financialData) {
        if (financialData.getType() == null) {
            throw new IllegalArgumentException("The type of expense / profit cannot be null");
        }

        if (financialData.getAmount() == null) {
            throw new IllegalArgumentException("The amount of expense / profit cannot be null");
        }

        financialData.setCreatedAt(LocalDateTime.now());

        return repository.save(financialData);
    }
}
