package br.edu.ifce.matheus.pacc.controller.financial_data;

import br.edu.ifce.matheus.pacc.controller.financial_data.requests.FinancialDataRequest;
import br.edu.ifce.matheus.pacc.controller.financial_data.responses.FinancialDataResponse;
import br.edu.ifce.matheus.pacc.service.FinancialDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet-information")
public class FinancialDataController {

    @Autowired
    private FinancialDataService financialDataService;

    @PostMapping
    public FinancialDataResponse createFinancialData(@RequestBody FinancialDataRequest request) {
        var financialData = financialDataService.createFinancialData(request.toFinancialData());
        return new FinancialDataResponse(financialData);
    }
}
