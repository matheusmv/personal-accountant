package br.edu.ifce.matheus.pacc.repository.mongo;

import br.edu.ifce.matheus.pacc.domain.FinancialData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDataRepositoryMongo extends MongoRepository<FinancialData, String> {

}
