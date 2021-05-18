package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.WalletFinancialsInformationResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.GetAllWalletExpenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GetAllWalletExpensesController {

    @Autowired
    private GetAllWalletExpenses getAllWalletExpenses;

    @GetMapping("/{username}/{walletName}/expenses")
    public ResponseEntity<WalletFinancialsInformationResponse> getAllWalletExpenses(@PathVariable String username,
                                                                                    @PathVariable String walletName) {
        var expenses = getAllWalletExpenses.execute(username, walletName);
        return new ResponseEntity<>(new WalletFinancialsInformationResponse(expenses), HttpStatus.OK);
    }
}
