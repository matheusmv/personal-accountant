package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.NewWalletRequest;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CreateNewWalletController {

    @Autowired
    private CreateNewWallet createNewWallet;

    @PostMapping("{username}/wallets")
    public ResponseEntity<Void> createNewWallet(@PathVariable String username,
                                                @RequestBody NewWalletRequest request) {
        var walletName = request.getName();
        createNewWallet.execute(walletName, username);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
