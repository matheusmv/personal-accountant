package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.NewWalletNameRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.WalletInformationResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.UpdateWalletName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UpdateWalletNameController {

    @Autowired
    private UpdateWalletName updateWalletName;

    @PutMapping("/{username}/{walletName}")
    public ResponseEntity<WalletInformationResponse> updateWalletName(@PathVariable String username,
                                                                      @PathVariable String walletName,
                                                                      @RequestBody NewWalletNameRequest request) {
        var newWalletName = request.getNewName();
        var updatedWallet = updateWalletName.execute(username, walletName, newWalletName);
        return new ResponseEntity<>(new WalletInformationResponse(updatedWallet), HttpStatus.OK);
    }
}
