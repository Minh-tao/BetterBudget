package com.budget.budgettracking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TransactionList {
    private ObservableList<Transaction> list;

    public TransactionList() {
        list = FXCollections.observableArrayList();
    }

    public boolean addTransaction(Transaction t) {
        return list.add(t);
    }

    public boolean removeTransaction(Transaction t) {
        return list.remove(t);
    }

    public int size() {
        return list.size();
    }

    public Transaction peekLast() {
        return list.get(size() - 1);
    }

    public Transaction peekFirst() {
        return list.get(0);
    }

    public ObservableList<Transaction> list() {
        return list;
    }

    public boolean isEmpty() {return list.isEmpty();}

    @Override
    public String toString() {return list.toString();}
}
