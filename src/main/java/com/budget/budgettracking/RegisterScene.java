package com.budget.budgettracking;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class RegisterScene extends Styling {

    private DataStorage dataStorage;
    private static Scene scene;
    private Text errorText;
    private BorderPane mainBorderPane;

    public RegisterScene(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
        mainBorderPane = new BorderPane();
        scene = createRegisterScene();
    }

    private Scene createRegisterScene() {
        // elements for Center Region of mainBorderPane

        errorText = new Text();
        setErrorTexts(errorText);

        GridPane centerGridPane = new GridPane();
        centerGridPane.setHgap(40);
        centerGridPane.setVgap(10);
        centerGridPane.setAlignment(Pos.CENTER);

        Label username = new Label("Username:* ");
        Label password = new Label("Password:* ");
        Label name = new Label("Name:* ");
        TextField usernameTxtField = new TextField("");
        PasswordField passwordTxtField = new PasswordField();
        TextField nameTxtField = new TextField("");
        Button registerButton = new Button("Register");
        Text loginNow = new Text("Already have an account? Log in.");

        // Set properties for elements
        // ...

        // Event handlers for registerButton and loginNow
        registerButton.setOnAction(event ->
                registerButtonEvent(usernameTxtField.getText(), passwordTxtField.getText(), nameTxtField.getText()));
        loginNow.setOnMouseClicked(event -> {
            // Navigate to LoginScene
        });

        // Add the elements to the GridPane (All labels are on 1st col, all text-fields on 2nd col)
        centerGridPane.add(username, 0, 0, 1, 1);
        centerGridPane.add(usernameTxtField, 1, 0, 1, 1);
        centerGridPane.add(password, 0, 1, 1, 1);
        centerGridPane.add(passwordTxtField, 1, 1, 1, 1);
        centerGridPane.add(name, 0, 2, 1, 1);
        centerGridPane.add(nameTxtField, 1, 2, 1, 1);
        centerGridPane.add(registerButton, 0, 3, 2, 1);
        centerGridPane.add(loginNow, 0, 4, 2, 1);

        // Set up the regions of the mainBorderPane
        mainBorderPane.setCenter(centerGridPane);
        mainBorderPane.setRight(null);
        mainBorderPane.setLeft(null);

        // Set the scene instance variable
        scene = new Scene(mainBorderPane, 600, 400);

        return scene;
    }

    private void registerButtonEvent(String username, String password, String name) {
        if (!dataStorage.checkUsernameAlreadyExisting(username)) {
            if (password.length() >= 6) {
                dataStorage.addUser(username, password);
                // Navigate to the login screen or another appropriate screen after successful registration
            } else {
                errorText.setText("Password must be at least 6 characters long.");
            }
        } else {
            errorText.setText("Username already registered.");
        }
    }

    public Scene getScene() {
        return scene;
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    protected Scene loginButtonEvent(String username, String password) {
        return null;
    }
}

