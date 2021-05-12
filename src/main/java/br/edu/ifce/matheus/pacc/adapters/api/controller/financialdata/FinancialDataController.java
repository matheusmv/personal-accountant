package br.edu.ifce.matheus.pacc.adapters.api.controller.financialdata;

import br.edu.ifce.matheus.pacc.adapters.api.controller.financialdata.requests.FinancialDataRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controller.financialdata.responses.FinancialDataResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewExpenseData;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewProfitData;
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
    private CreateNewExpenseData createNewExpenseData;
    @Autowired
    private CreateNewProfitData createNewProfitData;

    @PostMapping("/profits")
    public ResponseEntity<FinancialDataResponse> createProfitData(@RequestBody FinancialDataRequest request) {
        var savedFinancialData = createNewProfitData.execute(request.toFinancialData());

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedFinancialData.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new FinancialDataResponse(savedFinancialData));
    }

    @PostMapping("/expenses")
    public ResponseEntity<FinancialDataResponse> createExpenseData(@RequestBody FinancialDataRequest request) {
        var savedFinancialData = createNewExpenseData.execute(request.toFinancialData());

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedFinancialData.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new FinancialDataResponse(savedFinancialData));
    }
}
