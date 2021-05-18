package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.WalletFinancialsInformationResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.GetAllWalletProfits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GetAllWalletProfitsController {

    @Autowired
    private GetAllWalletProfits getAllWalletProfits;

    @GetMapping("/{username}/{walletName}/profits")
    public ResponseEntity<WalletFinancialsInformationResponse> getAllWalletProfits(@PathVariable String username,
                                                                                   @PathVariable String walletName) {
        var profits = getAllWalletProfits.execute(username, walletName);
        return new ResponseEntity<>(new WalletFinancialsInformationResponse(profits), HttpStatus.OK);
    }
}
