package br.edu.ifce.matheus.pacc.adapters.api.controller.wallet.responses;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import lombok.Value;

@Value
public class WalletResponse {
    String id;

    public WalletResponse(Wallet wallet){
        this.id = wallet.getId();
    }
}
