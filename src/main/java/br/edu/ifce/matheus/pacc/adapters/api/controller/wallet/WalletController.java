package br.edu.ifce.matheus.pacc.adapters.api.controller.wallet;

import br.edu.ifce.matheus.pacc.adapters.api.controller.wallet.requests.WalletRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controller.wallet.responses.WalletResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    @Autowired
    private CreateNewWallet createNewWallet;

    @PostMapping
    public ResponseEntity<WalletResponse> createWallet(@RequestBody WalletRequest request) {
        var savedWallet = createNewWallet.execute(request.toWallet());

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedWallet.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new WalletResponse(savedWallet));
    }
}
