package br.edu.ifce.matheus.pacc.domain.valueobjects;

import br.edu.ifce.matheus.pacc.domain.entities.enums.FinancialTransaction;
import br.edu.ifce.matheus.pacc.domain.exceptions.InvalidFinancialDataTypeException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class FinancialType {

    private String value;

    public FinancialType(String value) {
        if (value == null) {
            throw new InvalidFinancialDataTypeException("The type of expense / profit cannot be null.");
        }

        if (Arrays.stream(FinancialTransaction.values()).noneMatch(type -> type.name().equals(value.toUpperCase()))) {
            throw new InvalidFinancialDataTypeException("Invalid Financial Transaction!");
        }

        this.value = value;
    }
}
