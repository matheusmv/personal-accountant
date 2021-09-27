package br.edu.ifce.matheus.mongo.mongorepositories;

import br.edu.ifce.matheus.domain.FinancialData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDataMongoRepository extends MongoRepository<FinancialData, String> {

}
