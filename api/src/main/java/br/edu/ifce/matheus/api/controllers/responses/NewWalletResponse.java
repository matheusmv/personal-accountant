package br.edu.ifce.matheus.api.controllers.responses;

import br.edu.ifce.matheus.domain.Wallet;
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
