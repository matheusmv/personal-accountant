package br.edu.ifce.matheus.pacc.adapters.db.mongo;

import br.edu.ifce.matheus.pacc.domain.entities.FinancialData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FinancialDataMongoRepository extends MongoRepository<FinancialData, String> {

}
