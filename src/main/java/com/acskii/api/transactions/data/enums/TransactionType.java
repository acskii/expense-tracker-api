package com.acskii.api.transactions.data.enums;

import java.util.stream.Stream;

public enum TransactionType {
    /* Income */
    SALARY("Salary", true),
    GIFT("Gift", true),
    INVESTMENT("Investment", true),
    GENERAL_INCOME("Income", true),  // Default Income

    /* Expense */
    GROCERIES("Groceries", false),
    GAS("Gas utilities", false),
    ELECTRICITY("Electricity utilities", false),
    WATER("Water utilities", false),
    RENT("Rent", false),
    ENTERTAINMENT("Entertainment", false),
    HEALTHCARE("Healthcare", false),
    EDUCATION("Education", false),
    TRANSPORTATION("Transportation", false),
    GENERAL_EXPENSE("Expense", false);  // Default Expense

    private final String value;
    private final boolean income;

    TransactionType(String value, boolean income) {
        this.income = income;
        this.value = value;
    }

    public static TransactionType toEnum(String value) {
        return Stream.of(TransactionType.values())
                .filter(c -> c.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }

    public String getValue() {return value;}
    public boolean isIncome() {return income;}
}
