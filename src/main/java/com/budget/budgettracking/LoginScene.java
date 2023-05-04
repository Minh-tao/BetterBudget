package com.budget.budgettracking;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class LoginScene extends Styling {

    private DataStorage dataStorage;
    private Scene scene;
    private Text errorText;
    private BorderPane mainBorderPane;
    private StackPane headerStackPane;

    public LoginScene(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
        mainBorderPane = new BorderPane();
        headerStackPane = new StackPane();
        scene = createLoginScene();
    }


    private Scene createLoginScene() {


        headerStackPane = new StackPane();
        headerStackPane.setAlignment(Pos.CENTER);

        VBox loginForm = new VBox();
        VBox loginForm2 = new VBox();

        // elements for the Top Region of the mainBorderPane
        Text title = new Text("BetterBudget");

        // StackPane automatically centers the objects
        title.setFill(Color.web("#50C878"));
        title.setFont(titleFont);
        //center title vertically
        title.setTextOrigin(VPos.TOP);
        title.setTextAlignment(TextAlignment.CENTER);


        // The StackPane is used to layer items after each other

        headerStackPane.getChildren().clear();
        headerStackPane.getChildren().addAll(rectLarge(), title);



        // elements for the Center Region of mainBorderPane
        Label userLabel = new Label("Username: ");
        Label passLabel = new Label("Password: ");
        TextField username = new TextField();
        PasswordField password = new PasswordField();
        Text registerNow = new Text("Click here if you don't have an account yet.");
        Button loginButton = new Button("Log in");

        Insets padding = new Insets(25, 250, 25, 250);

        // For both Labels and TextFields
        userLabel.setLabelFor(username);
        passLabel.setLabelFor(password);

        userLabel.setFont(bodyFont);
        passLabel.setFont(bodyFont);


        username.setFont(bodyFont);
        password.setFont(bodyFont);

        username.setPrefHeight(40);
        password.setPrefHeight(40);


        // For log in button
        loginButton.setFont(bodyFont);
        buttonHoverEffectGreen(loginButton);
        loginButton.setTextFill(Color.WHITE);
        loginButton.setPrefWidth(100);

        // Set ActionEvent on the LogIn Button and register now text
        loginButton.setOnAction(event ->
                loginButtonEvent(username.getText(), password.getText()));
        //registerNow.setOnMouseClicked(event -> registerPane());

        // For register now text
        registerNow.setFont(smallFont);
        //maple red from color picker
        registerNow.setFill(Color.rgb(225, 0, 40));

        // For hovering the cursor on the elements
        username.setCursor(Cursor.TEXT);
        password.setCursor(Cursor.TEXT);
        loginButton.setCursor(Cursor.HAND);
        registerNow.setCursor(Cursor.HAND);

        // For the overall Log In Form (including the small text and the Login button in loginForm2 layout pane)
        loginForm.setSpacing(10.0);
        loginForm.setPadding(padding);
        loginForm.setStyle("-fx-margin-left: 20px;");

        loginForm2.setSpacing(10.0);
        loginForm2.setAlignment(Pos.BOTTOM_CENTER);
        loginForm2.getChildren().addAll(registerNow, loginButton);



        loginForm.getChildren().addAll(setErrorTexts(), userLabel, username, passLabel, password, loginForm2);
        loginButton.setOnAction(event -> loginButtonEvent(username.getText(), password.getText()));
        // Set up the 4 regions of border pane
        // Set up the 4 regions of border pane
        mainBorderPane.setTop(headerStackPane);
        mainBorderPane.setCenter(loginForm);
        mainBorderPane.setLeft(null);
        mainBorderPane.setRight(null);

        // Set the scene instance variable
        scene = new Scene(mainBorderPane, 600, 400);

        return scene;
    }

    @Override
    protected Scene loginButtonEvent(String username, String password) {
        User user = dataStorage.getUser(username, password);
        if (user != null) {
            // Navigate to the main pane, passing the user object
            BudgetInput budgetInput = new BudgetInput();
            errorText.setText(" ");
            return budgetInput.createScene();
        } else {
            errorText.setText("Wrong username or password");
        }
        return null;
    }


    private void mainPane(User user) {

    }

    public Scene getScene() {
        return scene;
    }
}
