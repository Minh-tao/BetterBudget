package com.budget.budgettracking;

public class Budget {
    private String name;
    private double limit;
    private double amount;

    enum Frequency {
        DAILY,
        WEEKLY,
        BIWEEKLY,
        MONTHLY,
        ANNUALLY
    }

    public Budget(String name, double amount, double limit) {
        this.name = name;
        this.limit = limit;
        this.amount = 0;
    }

    public String getName() {
        return name;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "name='" + name + '\'' +
                ", limit=" + limit +
                '}';
    }
}
