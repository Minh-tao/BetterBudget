package com.budget.budgettracking;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
        double totalBudgetAmount = 2000;
        budgetList.add(new Budget("Food", 250));
        budgetList.add(new Budget("Health", 100));
        budgetList.add(new Budget("Rent", 1000));
        budgetList.add(new Budget("Transportation", 120));
        budgetList.add(new Budget("Personal", 200));
        budgetList.add(new Budget("Misc", totalBudgetAmount - 1670));

        // input and view tabs
        BudgetInputTab budgetInputTab = new BudgetInputTab();
        BudgetView budgetViewTab = new BudgetView(totalBudgetAmount, budgetList);
//        TransactionInput transactionInputTab = new TransactionInput();
//        TransactionView transactionViewTab = new TransactionView();

//        tp.getTabs().addAll(budgetInputTab, budgetViewTab, transactionInputTab, transactionViewTab);
        tp.getTabs().addAll(budgetInputTab, budgetViewTab);
        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tp, Color.LIGHTBLUE);
        scene.getStylesheets().add("stylesheet.css");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    // Add a createScene() method that returns the Scene object
    // Add a createScene() method that returns the Scene object
    public Scene createScene() {
        final int WIDTH = 750;
        final int HEIGHT = 450;

        // Test data
        ObservableList<Budget> budgetList = FXCollections.observableArrayList();
        double totalBudgetAmount = 2000;
        budgetList.add(new Budget("Food", 250));
        budgetList.add(new Budget("Health", 100));
        budgetList.add(new Budget("Rent", 1000));
        budgetList.add(new Budget("Transportation", 120));
        budgetList.add(new Budget("Personal", 200));
        budgetList.add(new Budget("Misc", totalBudgetAmount - 1670));

        // Input and view tabs
        BudgetInputTab budgetInputTab = new BudgetInputTab();
        BudgetView budgetViewTab = new BudgetView(totalBudgetAmount, budgetList);
//        TransactionInput transactionInputTab = new TransactionInput();
//        TransactionView transactionViewTab = new TransactionView();

//        tp.getTabs().addAll(budgetInputTab, budgetViewTab, transactionInputTab, transactionViewTab);
        tp.getTabs().addAll(budgetInputTab, budgetViewTab);
        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tp, WIDTH, HEIGHT, Color.LIGHTBLUE);
        scene.getStylesheets().add("stylesheet.css");

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
