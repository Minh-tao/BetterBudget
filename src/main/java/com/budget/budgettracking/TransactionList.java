package com.budget.budgettracking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TransactionList {
    private ObservableList<Transaction> list;

    public TransactionList() {
        list = FXCollections.observableArrayList();
    }

//    public boolean addTransaction(Transaction t) {}

//    public boolean removeTransaction(Transaction t) {}

//public Transaction search() ??? maybe too much???

    public boolean isEmpty() {return list.isEmpty();}

    @Override
    public String toString() {return list.toString();}
}
