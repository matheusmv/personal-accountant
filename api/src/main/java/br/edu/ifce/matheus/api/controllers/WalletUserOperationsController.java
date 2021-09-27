package br.edu.ifce.matheus.api.controllers;

import br.edu.ifce.matheus.api.controllers.requests.NewFinancialDataRequest;
import br.edu.ifce.matheus.api.controllers.requests.NewWalletRequest;
import br.edu.ifce.matheus.api.controllers.requests.UpdateFinancialDataRequest;
import br.edu.ifce.matheus.api.controllers.requests.UpdateWalletRequest;
import br.edu.ifce.matheus.api.controllers.responses.DetailedWalletInformationResponse;
import br.edu.ifce.matheus.api.controllers.responses.FinancialInformationResponse;
import br.edu.ifce.matheus.api.controllers.responses.NewWalletResponse;
import br.edu.ifce.matheus.api.controllers.responses.WalletInformationResponse;
import br.edu.ifce.matheus.usecase.ports.driver.AddExpensesToAWallet;
import br.edu.ifce.matheus.usecase.ports.driver.AddProfitsToAWallet;
import br.edu.ifce.matheus.usecase.ports.driver.CreateAWallet;
import br.edu.ifce.matheus.usecase.ports.driver.DeleteAWallet;
import br.edu.ifce.matheus.usecase.ports.driver.GetAWalletForAUser;
import br.edu.ifce.matheus.usecase.ports.driver.ListWalletsForAUser;
import br.edu.ifce.matheus.usecase.ports.driver.RemoveAFinancialDataFromTheWallet;
import br.edu.ifce.matheus.usecase.ports.driver.UpdateAFinancialData;
import br.edu.ifce.matheus.usecase.ports.driver.UpdateAWalletName;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
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
    private final UpdateAFinancialData updateAFinancialData;
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

    @PutMapping("/{username}/{walletName}/{identificationCode}")
    public ResponseEntity<FinancialInformationResponse> updateAFinancialData(@PathVariable String username,
                                                                             @PathVariable String walletName,
                                                                             @PathVariable String identificationCode,
                                                                             @RequestBody UpdateFinancialDataRequest request) {
        var financialData = updateAFinancialData.execute(username, walletName, identificationCode, request.toFinancialData());
        return new ResponseEntity<>(new FinancialInformationResponse(financialData), HttpStatus.OK);
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

    @GetMapping("/{username}/{walletName}/download")
    public ResponseEntity<?> downloadAWallet(@PathVariable String username, @PathVariable String walletName) {
        var wallet = getAWalletForAUser.execute(username, walletName);

        var fileName = username + "-" + walletName + "-" + Instant.now() + ".json";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(wallet);
    }
}
