package br.edu.ifce.matheus.pacc.adapters.api.controller.financialdata;

import br.edu.ifce.matheus.pacc.adapters.api.controller.financialdata.requests.FinancialDataRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controller.financialdata.responses.FinancialDataResponse;
import br.edu.ifce.matheus.pacc.domain.ports.FinancialDataRepository;
import br.edu.ifce.matheus.pacc.domain.services.CreateNewFinancialData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/financials")
public class FinancialDataController {

    @Autowired
    private FinancialDataRepository financialDataRepository;

    @PostMapping
    public ResponseEntity<FinancialDataResponse> createFinancialData(@RequestBody FinancialDataRequest request) {
        var createNewFinancialData = new CreateNewFinancialData(financialDataRepository);
        var savedFinancialData = createNewFinancialData.execute(request.toFinancialData());

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedFinancialData.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new FinancialDataResponse(savedFinancialData));
    }
}
