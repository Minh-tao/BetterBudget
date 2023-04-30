package com.budget.budgettracking;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

    private static final int WIDTH = 750;
    private static final int HEIGHT = 450;

    public void start(Stage primaryStage) {
        // set up primary stage
        // show scenes, call other classes -> get scenes
        BudgetInput budgetInput = new BudgetInput();
        Scene scene = budgetInput.createScene();
        primaryStage.setResizable(false);
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Spending Visualizer");
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
