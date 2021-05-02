package br.edu.ifce.matheus.pacc.controller.wallet;

import br.edu.ifce.matheus.pacc.controller.wallet.requests.WalletRequest;
import br.edu.ifce.matheus.pacc.controller.wallet.responses.WalletResponse;
import br.edu.ifce.matheus.pacc.domain.Wallet;
import br.edu.ifce.matheus.pacc.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping
    public WalletResponse createWallet(@RequestBody WalletRequest request) {
        var wallet = walletService.createWallet(request.toWallet());
        return new WalletResponse(wallet);
    }

    @GetMapping(value = "/{name}")
    public Wallet getWallet(@PathVariable String name) {
        return walletService.findByName(name);
    }
}
