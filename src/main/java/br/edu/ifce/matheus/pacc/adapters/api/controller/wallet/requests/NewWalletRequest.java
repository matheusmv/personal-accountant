package br.edu.ifce.matheus.pacc.adapters.api.controller.wallet.requests;

import lombok.Data;

@Data
public class NewWalletRequest {
    private String name;
    private String ownerUsername;
}
