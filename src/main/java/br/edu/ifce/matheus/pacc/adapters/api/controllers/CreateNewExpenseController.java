package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.NewFinancialDataRequest;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewExpenseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CreateNewExpenseController {

    @Autowired
    private CreateNewExpenseData createNewExpenseData;

    @PostMapping("/{username}/{walletName}/expenses")
    public ResponseEntity<Void> createNewExpense(@PathVariable String username,
                                                 @PathVariable String walletName,
                                                 @RequestBody NewFinancialDataRequest request) {
        createNewExpenseData.execute(username, walletName, request.toFinancialData());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
