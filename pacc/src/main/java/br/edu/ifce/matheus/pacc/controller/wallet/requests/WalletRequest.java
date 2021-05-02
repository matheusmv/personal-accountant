package br.edu.ifce.matheus.pacc.controller.wallet.requests;

import br.edu.ifce.matheus.pacc.domain.Wallet;
import lombok.Value;

@Value
public class WalletRequest {
    String name;
    String ownerId;

    public Wallet toWallet() {
        var wallet = new Wallet();

        wallet.setName(name);

        return wallet;
    }
}
