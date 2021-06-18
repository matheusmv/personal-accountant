package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.NewFinancialDataRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.NewWalletRequest;
import br.edu.ifce.matheus.pacc.adapters.api.controllers.requests.UpdateWalletRequest;
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
    private final ListWalletsForAUser listWalletsForAUser;
    private final GetAWalletForAUser getAWalletForAUser;
    private final AddProfitsToAWallet addProfitsToAWallet;
    private final AddExpensesToAWallet addExpensesToAWallet;
    private final UpdateAWalletName updateAWalletName;
    private final RemoveAFinancialDataFromTheWallet removeAFinancialDataFromTheWallet;
    private final DeleteAWallet deleteAWallet;

    @PostMapping("{username}/wallets")
    public ResponseEntity<NewWalletResponse> createANewWallet(@PathVariable String username,
                                                              @RequestBody NewWalletRequest request) {
        var walletName = request.getName();
        var newWallet = createAWallet.execute(username, walletName);
        return new ResponseEntity<>(new NewWalletResponse(newWallet), HttpStatus.CREATED);
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

    @PostMapping("/{username}/{walletName}/profits")
    public ResponseEntity<FinancialInformationResponse> addProfitsToAWallet(@PathVariable String username,
                                                                            @PathVariable String walletName,
                                                                            @RequestBody NewFinancialDataRequest request) {
        var financialData = addProfitsToAWallet.execute(username, walletName, request.toFinancialData());
        return new ResponseEntity<>(new FinancialInformationResponse(financialData), HttpStatus.CREATED);
    }

    @PostMapping("/{username}/{walletName}/expenses")
    public ResponseEntity<FinancialInformationResponse> addExpensesToAWallet(@PathVariable String username,
                                                                             @PathVariable String walletName,
                                                                             @RequestBody NewFinancialDataRequest request) {
        var financialData = addExpensesToAWallet.execute(username, walletName, request.toFinancialData());
        return new ResponseEntity<>(new FinancialInformationResponse(financialData), HttpStatus.CREATED);
    }

    @PutMapping("/{username}/{walletName}")
    public ResponseEntity<WalletInformationResponse> updateAWalletName(@PathVariable String username,
                                                                       @PathVariable String walletName,
                                                                       @RequestBody UpdateWalletRequest request) {
        var newWalletName = request.getName();
        var wallet = updateAWalletName.execute(username, walletName, newWalletName);
        return new ResponseEntity<>(new WalletInformationResponse(wallet), HttpStatus.OK);
    }

    @DeleteMapping("/{username}/{walletName}/{identificationCode}")
    public ResponseEntity<Void> removeAFinancialDataFromTheWallet(@PathVariable String username,
                                                                  @PathVariable String walletName,
                                                                  @PathVariable String identificationCode) {
        removeAFinancialDataFromTheWallet.execute(username, walletName, identificationCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{username}/{walletName}")
    public ResponseEntity<Void> deleteAWallet(@PathVariable String username, @PathVariable String walletName) {
        deleteAWallet.execute(username, walletName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
