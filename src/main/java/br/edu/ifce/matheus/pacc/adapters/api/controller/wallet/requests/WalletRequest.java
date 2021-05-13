package br.edu.ifce.matheus.pacc.adapters.api.controller.wallet.requests;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import lombok.Data;

@Data
public class WalletRequest {

    private String name;
    private String ownerEmail;

    public Wallet toWallet() {
        var wallet = new Wallet();

        wallet.setName(name);
        wallet.setOwnerEmail(ownerEmail);

        return wallet;
    }
}
