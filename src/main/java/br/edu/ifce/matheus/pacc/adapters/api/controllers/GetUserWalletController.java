package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.WalletInformationResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.GetUserWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GetUserWalletController {

    @Autowired
    private GetUserWallet getUserWallet;

    @GetMapping("/{username}/{walletName}")
    public ResponseEntity<WalletInformationResponse> getUserWallet(@PathVariable String username,
                                                                   @PathVariable String walletName) {
        var wallet = getUserWallet.execute(username, walletName);
        return new ResponseEntity<>(new WalletInformationResponse(wallet), HttpStatus.OK);
    }
}
