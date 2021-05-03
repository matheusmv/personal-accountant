package br.edu.ifce.matheus.pacc.domain.entities.enums;

import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidFinancialDataTypeException;

public enum FinancialTransaction {
    PROFIT(1),
    EXPENSE(2);

    private final int code;

    FinancialTransaction(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static FinancialTransaction valueOf(int code) {
        for (FinancialTransaction value : FinancialTransaction.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new InvalidFinancialDataTypeException("Invalid Financial Transaction!");
    }
}
