package com.budget.budgettracking;

import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

    private static final int WIDTH = 750;
    private static final int HEIGHT = 450;
    private static final String LOGO_PATH = "Logo.gif";

    public void start(Stage primaryStage) {
        // set up primary stage
        // show scenes, call other classes -> get scenes
        Image icon = new Image("Logo.png");
        BudgetInput budgetInput = new BudgetInput();
        Scene scene = budgetInput.createScene();

        BorderPane root = new BorderPane();

        Scene scene2 = new Scene(root, 600, 400);
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene2);
        primaryStage.setTitle("Better Budget");

        // Create the intro screen with the logo
        ImageView logo = new ImageView(new Image(LOGO_PATH));
        logo.setPreserveRatio(true);
        logo.setFitWidth(200);

// Create the welcome text
        Text welcomeText = new Text("Welcome to Better Budget!");
// Set font style to font.ttf
        welcomeText.setFont(Font.loadFont(getClass().getResourceAsStream("/fonts/font.ttf"), 24));
        welcomeText.setFill(Color.web("#00BB62"));

        // Create a thin light green line
        Separator separator = new Separator();
        separator.setMaxWidth(200);
        separator.setMinHeight(1);
        separator.setStyle("-fx-background-color: #00BB62");

        VBox introContent = new VBox(5, logo, separator, welcomeText);
        introContent.setAlignment(Pos.CENTER);

        StackPane introPane = new StackPane(introContent);
        introPane.setAlignment(Pos.CENTER);
        root.setCenter(introPane);

        primaryStage.setResizable(false);
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);

        // Apply the custom logo transition
        LogoTransition logoTransition = new LogoTransition(logo, welcomeText, separator, introPane);
        logoTransition.setOnFinished(event -> {
            primaryStage.setScene(scene);
        });
        logoTransition.play();

        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("BetterBudget Spending Tracker");

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
