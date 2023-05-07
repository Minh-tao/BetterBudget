package com.budget.budgettracking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BudgetList {
    private ObservableList<Budget> list;

    public BudgetList() {
        list = FXCollections.observableArrayList();
    }

    public boolean addBudget(Budget b) {
        return list.add(b);
    }

    public boolean removeBudget(Budget b) {
        return list.remove(b);
    }

    public boolean isEmpty() {return list.isEmpty();}

    @Override
    public String toString() {return list.toString();}
}
