package br.edu.ifce.matheus.mongo;

import br.edu.ifce.matheus.domain.FinancialData;
import br.edu.ifce.matheus.mongo.mongorepositories.FinancialDataMongoRepository;
import br.edu.ifce.matheus.usecase.ports.driven.FinancialDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialDataRepositoryImpl implements FinancialDataRepository {

    @Autowired
    private FinancialDataMongoRepository repository;

    @Override
    public FinancialData save(FinancialData financialData) {
        return repository.save(financialData);
    }
}
