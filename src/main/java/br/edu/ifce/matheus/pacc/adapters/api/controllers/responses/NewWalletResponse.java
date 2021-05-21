package br.edu.ifce.matheus.pacc.adapters.api.controllers.responses;

import br.edu.ifce.matheus.pacc.domain.entities.Wallet;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewWalletResponse {

    private String name;
    private LocalDateTime createdAt;

    public NewWalletResponse(Wallet wallet) {
        this.name = wallet.getName();
        this.createdAt = wallet.getCreatedAt();
    }
}
