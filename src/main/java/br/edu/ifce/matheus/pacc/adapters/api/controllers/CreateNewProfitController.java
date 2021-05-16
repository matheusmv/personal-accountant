package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.NewFinancialDataRequest;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewProfitData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CreateNewProfitController {

    @Autowired
    private CreateNewProfitData createNewProfitData;

    @PostMapping("/{username}/{walletName}/profits")
    public ResponseEntity<Void> createNewProfit(@PathVariable String username,
                                                @PathVariable String walletName,
                                                @RequestBody NewFinancialDataRequest request) {
        createNewProfitData.execute(username, walletName, request.toFinancialData());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
