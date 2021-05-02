package br.edu.ifce.matheus.pacc.controller.wallet.responses;

import br.edu.ifce.matheus.pacc.domain.Wallet;
import lombok.Value;

@Value
public class WalletResponse {
    String id;

    public WalletResponse(Wallet wallet){
        this.id = wallet.getId();
    }
}
