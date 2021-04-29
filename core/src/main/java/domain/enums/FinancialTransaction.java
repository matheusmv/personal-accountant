package domain.enums;

public enum FinancialTransaction {
    PROFIT(1),
    EXPENSE(2);

    private final int code;

    FinancialTransaction(int code) {
        this.code = code;
    }

    public int getCode() {
        return  code;
    }

    public static FinancialTransaction valueOf(int code) {
        for (FinancialTransaction value : FinancialTransaction.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Financial Transaction!");
    }
}
