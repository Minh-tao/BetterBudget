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
        double totalBudgetAmount = 2000;
        budgetList.add(new Budget("Food", 250));
        budgetList.add(new Budget("Health", 100));
        budgetList.add(new Budget("Rent", 1000));
        budgetList.add(new Budget("Transportation", 120));
        budgetList.add(new Budget("Personal", 200));
        budgetList.add(new Budget("Misc", totalBudgetAmount - 1670));

        // input and view tabs
        BudgetInputTab budgetInputTab = new BudgetInputTab(dataStorage);
        BudgetView budgetViewTab = new BudgetView(dataStorage);

        // TransactionInput

        TransactionList transactionList = new TransactionList();

        transactionList.addTransaction(new Transaction("Starcraft 2", 25, "Entertainment", LocalDate.now()));
        transactionList.addTransaction(new Transaction("Rainbow Six Siege", 100, "Entertainment", LocalDate.now()));
        transactionList.addTransaction(new Transaction("Warcraft 3", 60, "Entertainment", LocalDate.now().minusMonths(1)));
        transactionList.addTransaction(new Transaction("Battlefield 4", 15, "Entertainment", LocalDate.now().minusMonths(2)));
        transactionList.addTransaction(new Transaction("Crying Suns", 25, "Entertainment", LocalDate.now().minusMonths(2)));
        transactionList.addTransaction(new Transaction("Minecraft", 25, "Entertainment", LocalDate.now().minusMonths(3)));
        transactionList.addTransaction(new Transaction("Signalis", 20, "Entertainment", LocalDate.now().minusMonths(2)));
        transactionList.addTransaction(new Transaction("Undertale", 20, "Entertainment", LocalDate.now().minusMonths(3)));

        transactionList.addTransaction(new Transaction("Protein powder", 12, "Health and wellness", LocalDate.now()));
        transactionList.addTransaction(new Transaction("Treadmill", 100, "Health and wellness", LocalDate.now().minusMonths(4)));
        transactionList.addTransaction(new Transaction("Blender", 30, "Health and wellness", LocalDate.now()));
        transactionList.addTransaction(new Transaction("Weights", 25, "Health and wellness", LocalDate.now().minusMonths(1)));
        transactionList.addTransaction(new Transaction("Running Shoes", 80, "Health and wellness", LocalDate.now().minusMonths(3)));
        transactionList.addTransaction(new Transaction("Jump Rope", 25, "Health and wellness", LocalDate.now().minusMonths(2)));

        transactionList.addTransaction(new Transaction("China: Crony Capitalism", 25, "Education", LocalDate.now()));
        transactionList.addTransaction(new Transaction("Wacom Tablet", 75, "Education", LocalDate.now().plusMonths(2)));
        transactionList.addTransaction(new Transaction("Apple Pen", 80, "Education", LocalDate.now().minusMonths(1)));
        transactionList.addTransaction(new Transaction("iPad", 150, "Education", LocalDate.now().minusMonths(2)));

        TransactionInput transactionInputTab = new TransactionInput(transactionList);
        TransactionView transactionViewTab = new TransactionView(transactionList.getList());

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
    // Add a createScene() method that returns the Scene object
    public Scene createScene() {
        final int WIDTH = 750;
        final int HEIGHT = 450;

        double totalBudgetAmount = dataStorage.getLoggedUser().getTotalLimit();
        ObservableList<Budget> budgetList = FXCollections.observableArrayList(dataStorage.getBudgets());
        // Test data
/*        ObservableList<Budget> budgetList = FXCollections.observableArrayList();
        double totalBudgetAmount = 2000;
        budgetList.add(new Budget("Food", 250));
        budgetList.add(new Budget("Health", 100));
        budgetList.add(new Budget("Rent", 1000));
        budgetList.add(new Budget("Transportation", 120));
        budgetList.add(new Budget("Personal", 200));
        budgetList.add(new Budget("Misc", totalBudgetAmount - 1670));*/

        TransactionList transactionList = new TransactionList();

        transactionList.addTransaction(new Transaction("Starcraft 2", 25, "Entertainment", LocalDate.now()));
        transactionList.addTransaction(new Transaction("Rainbow Six Siege", 100, "Entertainment", LocalDate.now()));
        transactionList.addTransaction(new Transaction("Warcraft 3", 60, "Entertainment", LocalDate.now().minusMonths(1)));
        transactionList.addTransaction(new Transaction("Battlefield 4", 15, "Entertainment", LocalDate.now().minusMonths(2)));
        transactionList.addTransaction(new Transaction("Crying Suns", 25, "Entertainment", LocalDate.now().minusMonths(2)));
        transactionList.addTransaction(new Transaction("Minecraft", 25, "Entertainment", LocalDate.now().minusMonths(3)));
        transactionList.addTransaction(new Transaction("Signalis", 20, "Entertainment", LocalDate.now().minusMonths(2)));
        transactionList.addTransaction(new Transaction("Undertale", 20, "Entertainment", LocalDate.now().minusMonths(3)));

        transactionList.addTransaction(new Transaction("Protein powder", 12, "Health and wellness", LocalDate.now()));
        transactionList.addTransaction(new Transaction("Treadmill", 100, "Health and wellness", LocalDate.now().minusMonths(4)));
        transactionList.addTransaction(new Transaction("Blender", 30, "Health and wellness", LocalDate.now()));
        transactionList.addTransaction(new Transaction("Weights", 25, "Health and wellness", LocalDate.now().minusMonths(1)));
        transactionList.addTransaction(new Transaction("Running Shoes", 80, "Health and wellness", LocalDate.now().minusMonths(3)));
        transactionList.addTransaction(new Transaction("Jump Rope", 25, "Health and wellness", LocalDate.now().minusMonths(2)));

        transactionList.addTransaction(new Transaction("China: Crony Capitalism", 25, "Education", LocalDate.now()));
        transactionList.addTransaction(new Transaction("Wacom Tablet", 75, "Education", LocalDate.now().plusMonths(2)));
        transactionList.addTransaction(new Transaction("Apple Pen", 80, "Education", LocalDate.now().minusMonths(1)));
        transactionList.addTransaction(new Transaction("iPad", 150, "Education", LocalDate.now().minusMonths(2)));
        // Input and view tabs
        BudgetInputTab budgetInputTab = new BudgetInputTab(dataStorage);
        BudgetView budgetViewTab = new BudgetView(dataStorage);
        TransactionInput transactionInputTab = new TransactionInput(transactionList);
        TransactionView transactionViewTab = new TransactionView(transactionList.getList());

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
