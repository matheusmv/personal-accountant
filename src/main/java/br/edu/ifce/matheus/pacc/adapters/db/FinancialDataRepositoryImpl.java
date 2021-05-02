package br.edu.ifce.matheus.pacc.adapters.db;

import br.edu.ifce.matheus.pacc.adapters.db.mongo.FinancialDataMongoRepository;
import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import br.edu.ifce.matheus.pacc.domain.ports.FinancialDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FinancialDataRepositoryImpl implements FinancialDataRepository {

    @Autowired
    private FinancialDataMongoRepository repository;


    @Override
    public FinancialData saveFinancialData(FinancialData financialData) {
        return repository.save(financialData);
    }
}
