package com.budget.budgettracking;

public class Budget {
    private String name;
    private double amount;
    private double current;

    enum Frequency {
        DAILY,
        WEEKLY,
        BIWEEKLY,
        MONTHLY,
        ANNUALLY
    }

    public Budget(String name, double amount, double current) {
        this.name = name;
        this.amount = amount;
        this.current = current;
    }
    public Budget(String name, double amount) {
        this.name = name;
        this.amount = amount;
        this.current = 0;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
