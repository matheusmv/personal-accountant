package br.edu.ifce.matheus.pacc.adapters.api.controller.wallet;

import br.edu.ifce.matheus.pacc.adapters.api.controller.wallet.requests.WalletRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controller.wallet.responses.WalletResponse;
import br.edu.ifce.matheus.pacc.domain.ports.WalletRepository;
import br.edu.ifce.matheus.pacc.domain.services.CreateNewWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    @Autowired
    private WalletRepository walletRepository;

    @PostMapping
    public WalletResponse createWallet(@RequestBody WalletRequest request) {
        CreateNewWallet createNewWallet = new CreateNewWallet(walletRepository);
        var savedWallet = createNewWallet.execute(request.toWallet());
        return new WalletResponse(savedWallet);
    }
}
