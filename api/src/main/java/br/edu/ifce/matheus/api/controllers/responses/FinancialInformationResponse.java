package br.edu.ifce.matheus.api.controllers.responses;

import br.edu.ifce.matheus.domain.FinancialData;
import br.edu.ifce.matheus.domain.enums.FinancialTransaction;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FinancialInformationResponse {

    private String identificationCode;
    private FinancialTransaction type;
    private String description;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public FinancialInformationResponse(FinancialData financialData) {
        this.identificationCode = financialData.getIdentificationCode();
        this.type = financialData.getType();
        this.description = financialData.getDescription();
        this.amount = financialData.getAmount();
        this.createdAt = financialData.getCreatedAt();
    }
}
