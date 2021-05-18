package br.edu.ifce.matheus.pacc.adapters.api.controllers;

import br.edu.ifce.matheus.pacc.adapters.api.controllers.responses.WalletInformationResponse;
import br.edu.ifce.matheus.pacc.domain.ports.driven.UserRepository;
import br.edu.ifce.matheus.pacc.domain.ports.driven.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GetUserWalletController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletRepository walletRepository;

    @GetMapping("/{username}/{walletName}")
    public ResponseEntity<WalletInformationResponse> getUserWallet(@PathVariable String username,
                                                                    @PathVariable String walletName) {
        var userExists = userRepository.findUserByUsername(username);

        if (userExists.isPresent()) {
            var userId = userExists.get().getId();
            var walletExists = walletRepository.findWalletByNameAndOwnerId(walletName, userId);

            if (walletExists.isPresent()) {
                var wallet = walletExists.get();
                var walletInformation = new WalletInformationResponse(
                        wallet.getName(),
                        wallet.getFinancials()
                );

                return new ResponseEntity<>(walletInformation, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
