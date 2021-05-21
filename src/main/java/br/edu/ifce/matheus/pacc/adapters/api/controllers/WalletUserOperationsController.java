package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.NewFinancialDataRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.NewWalletRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.DetailedWalletInformationResponse;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.FinancialInformationResponse;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.NewWalletResponse;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.WalletInformationResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driver.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class WalletUserOperationsController {

    private final CreateAWallet createAWallet;
    private final AddProfitsToAWallet addProfitsToAWallet;
    private final AddExpensesToAWallet addExpensesToAWallet;
    private final ListWalletsForAUser listWalletsForAUser;
    private final GetAWalletForAUser getAWalletForAUser;

    @PostMapping("{username}/wallets")
    public ResponseEntity<NewWalletResponse> createANewWallet(@PathVariable String username,
                                                              @RequestBody NewWalletRequest request) {
        var walletName = request.getName();
        var newWallet = createAWallet.execute(username, walletName);
        return new ResponseEntity<>(new NewWalletResponse(newWallet), HttpStatus.CREATED);
    }

    @PostMapping("/{username}/{walletName}/wallets/profits")
    public ResponseEntity<FinancialInformationResponse> addProfitsToAWallet(@PathVariable String username,
                                                                            @PathVariable String walletName,
                                                                            @RequestBody NewFinancialDataRequest request) {
        var financialData = addProfitsToAWallet.execute(username, walletName, request.toFinancialData());
        return new ResponseEntity<>(new FinancialInformationResponse(financialData), HttpStatus.CREATED);
    }

    @PostMapping("/{username}/{walletName}/wallets/expenses")
    public ResponseEntity<FinancialInformationResponse> addExpensesToAWallet(@PathVariable String username,
                                                                             @PathVariable String walletName,
                                                                             @RequestBody NewFinancialDataRequest request) {
        var financialData = addExpensesToAWallet.execute(username, walletName, request.toFinancialData());
        return new ResponseEntity<>(new FinancialInformationResponse(financialData), HttpStatus.CREATED);
    }

    @GetMapping("/{username}/wallets")
    public ResponseEntity<List<WalletInformationResponse>> listWalletsForAUser(@PathVariable String username) {
        var listOfWallets = listWalletsForAUser.execute(username)
                .stream()
                .map(WalletInformationResponse::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(listOfWallets, HttpStatus.OK);
    }

    @GetMapping("/{username}/{walletName}")
    public ResponseEntity<DetailedWalletInformationResponse> getAWalletForAUser(@PathVariable String username,
                                                                                @PathVariable String walletName) {
        var wallet = getAWalletForAUser.execute(username, walletName);
        return new ResponseEntity<>(new DetailedWalletInformationResponse(wallet), HttpStatus.OK);
    }
}
