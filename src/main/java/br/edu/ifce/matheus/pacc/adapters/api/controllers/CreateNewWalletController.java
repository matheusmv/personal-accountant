package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.NewWalletRequest;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CreateNewWalletController {

    @Autowired
    private CreateNewWallet createNewWallet;

    @PostMapping("/wallets")
    public ResponseEntity<Void> createNewWallet(@RequestBody NewWalletRequest request) {
        var walletName = request.getName();
        var ownerUsername = request.getOwnerUsername();
        createNewWallet.execute(walletName, ownerUsername);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
