package com.budget.budgettracking;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MainWindow extends Application {

    TabPane tp = new TabPane();

    private DataStorage dataStorage; // <-- Add dataStorage field

    // Add a constructor that takes DataStorage as a parameter
    public MainWindow(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public void start(Stage stage) {
        final int WIDTH = 750;
        final int HEIGHT = 450;

        // test
        ObservableList<Budget> budgetList = FXCollections.observableArrayList();
        // input and view tabs
        BudgetInputTab budgetInputTab = new BudgetInputTab(dataStorage);
        BudgetView budgetViewTab = new BudgetView(dataStorage);

        // TransactionInput

        TransactionList transactionList = new TransactionList();

        TransactionInput transactionInputTab = new TransactionInput(transactionList);
        TransactionView transactionViewTab = new TransactionView(dataStorage);

//        tp.getTabs().addAll(budgetInputTab, budgetViewTab, transactionInputTab, transactionViewTab);
        tp.getTabs().addAll(budgetInputTab, budgetViewTab, transactionInputTab, transactionViewTab);
        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tp, Color.LIGHTBLUE);
        scene.getStylesheets().add("stylesheet.css");
        stage.setTitle("Budget Tracker");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setScene(scene);
        stage.show();
    }



    // Add a createScene() method that returns the Scene object
    public Scene createScene() {
        final int WIDTH = 750;
        final int HEIGHT = 450;

        // Input and view tabs
        BudgetInputTab budgetInputTab = new BudgetInputTab(dataStorage);
        BudgetView budgetViewTab = new BudgetView(dataStorage);
        TransactionInput transactionInputTab = new TransactionInput(dataStorage);
        TransactionView transactionViewTab = new TransactionView(dataStorage);

        tp.getTabs().addAll(budgetInputTab, budgetViewTab, transactionInputTab, transactionViewTab);
        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tp, WIDTH, HEIGHT, Color.LIGHTBLUE);
        scene.getStylesheets().add("stylesheet.css");

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
