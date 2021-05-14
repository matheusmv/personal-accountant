package br.edu.ifce.matheus.pacc.adapters.api.controller.wallet;

import br.edu.ifce.matheus.pacc.adapters.api.controller.wallet.requests.FinancialDataRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controller.wallet.requests.NewWalletRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controller.wallet.responses.WalletResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewExpenseData;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewProfitData;
import br.edu.ifce.matheus.pacc.domain.ports.driver.CreateNewWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    @Autowired
    private CreateNewWallet createNewWallet;
    @Autowired
    private CreateNewExpenseData createNewExpenseData;
    @Autowired
    private CreateNewProfitData createNewProfitData;

    @PostMapping
    public ResponseEntity<WalletResponse> createWallet(@RequestBody NewWalletRequest request) {
        var walletName = request.getName();
        var ownerUsername = request.getOwnerUsername();
        var savedWallet = createNewWallet.execute(walletName, ownerUsername);
        return new ResponseEntity<>(new WalletResponse(savedWallet), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/profits")
    public ResponseEntity<Void> createProfitData(@PathVariable String id,
                                                 @RequestBody FinancialDataRequest request) {
        createNewProfitData.execute(id, request.toFinancialData());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}/expenses")
    public ResponseEntity<Void> createExpenseData(@PathVariable String id,
                                                  @RequestBody FinancialDataRequest request) {
        createNewExpenseData.execute(id, request.toFinancialData());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
