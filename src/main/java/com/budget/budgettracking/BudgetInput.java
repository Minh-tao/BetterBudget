package com.budget.budgettracking;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Objects;

public class BudgetInput extends Application{
    // visual components
        TabPane tp = new TabPane();
        Tab inputTab = new Tab();
        BudgetView viewTab;
        GridPane outerGridpane = new GridPane();
        VBox inputPanel = new VBox();
        FlowPane flow = new FlowPane();
        VBox nameBox = new VBox();
        VBox amountBox = new VBox();
        VBox timeBox = new VBox();
        VBox tableBox = new VBox();
        HBox buttonBox = new HBox();
        HBox addBox = new HBox();
        HBox totalBudgetBox = new HBox();
        HBox totalInner = new HBox();
        HBox totalButtonBox = new HBox();
        HBox addHeaderBox = new HBox();
        HBox tableHeaderBox = new HBox();
        HBox categoryLabelBox = new HBox();

        // widgets
        Label totalLabel = new Label("Total Monthly Budget");
        Label addHeaderLabel = new Label("Budget");
        Label tableHeaderLabel = new Label("Current Categories");
        Label nameLabel = new Label("Name");
        Label amountLabel = new Label("Amount");
        Label categoryLabel = new Label("Add Category");
        Label timeLabel = new Label("Time Period"); // frequency, occurency, repeating:

        Separator separator = new Separator();

        TextField totalField = new TextField("");
        TextField nameField = new TextField("Name");
        TextField amountField = new TextField("");
        TextField otherField = new TextField("Other");

        // Create the radio buttons
        RadioButton dailyRadioButton = new RadioButton("Daily");
        RadioButton weeklyRadioButton = new RadioButton("Weekly");
        RadioButton monthlyRadioButton = new RadioButton("Monthly");
        RadioButton annuallyRadioButton = new RadioButton("Annually");
        RadioButton otherRadioButton = new RadioButton("Other");
        ToggleGroup toggleGroup = new ToggleGroup();

        Button totalButton = new Button("Update Total");
        Button removeButton = new Button("Remove");
        Button addButton = new Button("Add");
        Button viewButton = new Button("View Budget Overview");
        Button quitButton = new Button("Quit");

        // initial budget, TODO should be loaded from user data
        double totalBudgetAmount = 0.0;

        // dropdown for budget category name
        ObservableList<String> nameList = FXCollections.observableArrayList( // list of categories
                "Clothing", "Debt payments", "Education", "Entertainment", "Food", "Gifts and donations",
                "Health and wellness", "Housing", "Insurance", "Personal care",
                "Savings", "Taxes", "Transportation", "Miscellaneous"
        );
        ComboBox<String> nameCombo = new ComboBox<>(nameList);

        // table of budget categories
        ObservableList<Budget> budgetList = FXCollections.observableArrayList();
        TableView<Budget> budgetTable = new TableView<>(budgetList);

    /**
     *
     * @return scene budget input window
     */
    public Scene createScene() {

        allStyling();
        gridConstraints();
        populate();
        setHandlers();
        tableSetup();
        radioButtons();
        // tooltips

        // tab
        inputTab.setText("Input");
        inputTab.setContent(outerGridpane);
        // viewTab = createViewTab();
        // viewTab.setText("Overview");
        tp.getTabs().addAll(inputTab);
        tp.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        VBox root = new VBox(tp);
        Scene scene = new Scene(root, Color.LIGHTBLUE);
        scene.getStylesheets().add("stylesheet.css");
        return scene;
    }

    private void radioButtons() {
        dailyRadioButton.setToggleGroup(toggleGroup);
        weeklyRadioButton.setToggleGroup(toggleGroup);
        monthlyRadioButton.setToggleGroup(toggleGroup);
        annuallyRadioButton.setToggleGroup(toggleGroup);
        otherRadioButton.setToggleGroup(toggleGroup);
    }

    private void allStyling() {
        // box alignment
        nameBox.setAlignment(Pos.CENTER);
        amountBox.setAlignment(Pos.CENTER);
        timeBox.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        addBox.setAlignment(Pos.CENTER);
        totalInner.setAlignment(Pos.CENTER);
        totalButtonBox.setAlignment(Pos.CENTER);
        totalBudgetBox.setAlignment(Pos.CENTER);
        tableBox.setAlignment(Pos.TOP_CENTER);
        inputPanel.setAlignment(Pos.CENTER);
        addHeaderBox.setAlignment(Pos.BOTTOM_CENTER);
        tableHeaderBox.setAlignment(Pos.BOTTOM_CENTER);
        categoryLabelBox.setAlignment(Pos.CENTER);

        // label styling (sizes, font)
        Font titlefont = Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf"), 18);
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf"), 14);

        addHeaderLabel.getStyleClass().add("title");
        tableHeaderLabel.getStyleClass().add("title");
        categoryLabel.getStyleClass().add("title");
        nameLabel.getStyleClass().add("custom-label");
        totalLabel.getStyleClass().add("custom-label");
        amountLabel.getStyleClass().add("custom-label");
        timeLabel.getStyleClass().add("custom-label");
        categoryLabel.getStyleClass().add("custom-label");

        // widget styling
        nameField.setMaxWidth(125);
        nameField.setPrefWidth(125);
        nameCombo.setMaxWidth(175);
        nameCombo.setPrefWidth(175);
        nameCombo.setEditable(true);
        amountField.setMaxWidth(75);
        amountField.setPromptText("$");
        totalField.setPromptText("$");
        totalField.setMaxWidth(75);
        totalField.setPrefHeight(25);
        totalField.setAlignment(Pos.CENTER_LEFT);
        quitButton.setPrefWidth(75);
        buttonBox.setSpacing(10);
        flow.setAlignment(Pos.TOP_CENTER);
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(170);
//        flow.setStyle("-fx-background-color: #00BB62;");
//        inputPanel.setPadding(new Insets(10, 10, 10, 10));
        inputPanel.setSpacing(10);
        totalBudgetBox.setSpacing(10);
//        totalBudgetBox.setPadding(new Insets(10, 10, 10, 10));
//        totalBudgetBox.setMaxWidth(340);
        totalInner.setSpacing(5);
        totalInner.setPadding(new Insets(10, 10, 10, 10));
        addButton.setFont(font);
        amountField.setFont(font);
        quitButton.setFont(font);
        totalField.setFont(font);
        otherField.setPromptText("Enter frequency");
        otherField.setDisable(true);

        // box/grid styling
        outerGridpane.setAlignment(Pos.CENTER);
        outerGridpane.setStyle("-fx-background-color: linear-gradient(to bottom, #F2FFDB, #00BB62);");
        outerGridpane.setPadding(new Insets(10, 10, 10, 10));
        outerGridpane.setHgap(10);
        outerGridpane.setVgap(10);
//        inputPanel.getStyleClass().add("inputPanel");
        inputPanel.setStyle(
                "-fx-border-style: solid inside;"
                +"-fx-border-width: 1;"
                +"-fx-border-color: black;"
                +"-fx-background-color: LIGHTCYAN;");
        tableBox.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: black;"
                + "-fx-background-color: LIGHTGREY;");
    }

    private void gridConstraints() {
        outerGridpane.setGridLinesVisible(false);

        // outerGridPane styling
        ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(40);
        ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(60);
        RowConstraints row1 = new RowConstraints();
//            row1.setMaxHeight(25);
//            row1.setPrefHeight(0);
//            row1.setVgrow(Priority.SOMETIMES);
        RowConstraints row2 = new RowConstraints();
//            row2.setMaxHeight(261);
//            row2.setMinHeight(10);
            row2.setPrefHeight(20);
//            row2.setVgrow(Priority.SOMETIMES);
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();

        outerGridpane.getColumnConstraints().addAll(column1, column2);
        outerGridpane.getRowConstraints().addAll(row1, row2, row3, row4, row5);
    }

    private void populate() {
        // add to grid
//        totalInner.getChildren().addAll(totalLabel, totalField);
        totalButtonBox.getChildren().addAll(totalButton);
        categoryLabelBox.getChildren().addAll(categoryLabel);
        totalBudgetBox.getChildren().addAll(totalLabel, totalField);
        nameBox.getChildren().addAll(nameLabel, nameCombo);
        amountBox.getChildren().addAll(amountLabel, amountField);
        timeBox.getChildren().addAll(timeLabel, flow);
        addBox.getChildren().addAll(addButton);
        tableBox.getChildren().addAll(budgetTable);
        buttonBox.getChildren().addAll(removeButton, viewButton, quitButton);
        flow.getChildren().addAll(dailyRadioButton,
                weeklyRadioButton, monthlyRadioButton,
                annuallyRadioButton, otherRadioButton, otherField);
        outerGridpane.add(totalBudgetBox, 0, 0, 2, 1);
        addHeaderBox.getChildren().add(addHeaderLabel);
        tableHeaderBox.getChildren().add(tableHeaderLabel);
//        outerGridpane.add(addHeaderBox, 0, 1);
        outerGridpane.add(inputPanel, 0, 2);
            inputPanel.getChildren().addAll(totalBudgetBox, totalButtonBox, separator, categoryLabelBox, nameBox, amountBox, addBox);
        outerGridpane.add(tableHeaderBox, 1, 1);
        outerGridpane.add(tableBox, 1, 2);
        outerGridpane.add(buttonBox, 1, 3);

    }

    private void tableSetup() {
        TableColumn<Budget, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Budget, String> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Budget, String> percentColumn = new TableColumn<>("% of Total Budget");
        percentColumn.setCellValueFactory(cellData -> {
            Budget budget = cellData.getValue();
            double percentage = (budget.getAmount() / totalBudgetAmount) * 100;
            return new SimpleStringProperty(String.format("%.2f%%", percentage));
        });

        TableColumn<Budget, String> timeColumn = new TableColumn<>("Time Period");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        budgetTable.setEditable(false);
        budgetTable.setItems(budgetList);
        budgetTable.getColumns().addAll(nameColumn, amountColumn, percentColumn); // removed timeColumn
        budgetTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void otherHandler() {// Enable the TextField when the "Other" radio button is selected
        otherRadioButton.setOnAction(event -> {
            otherField.setDisable(!otherRadioButton.isSelected());
            System.out.println("other pressed");
        });
    }

    private void createView() {
        BudgetView tab = new BudgetView(tp, totalBudgetAmount, budgetList);
        if (tp.getTabs().size() <= 1) {
            tp.getTabs().add(tab);
        } else { tp.getTabs().set(1, tab); }
    }

    private void overviewHandler() {
        createView();
        tp.getSelectionModel().select(1); //index, or tab name, or selectNext()
    }

    private void addHandler() {
        if (nameCombo.getValue() == null) {
            System.out.println("Invalid name");
            return;
        }
        String name = (String)nameCombo.getValue();
        nameCombo.setValue(null);

        if (amountField.getText().isEmpty() || amountField.getText().equals("$")) {
            System.out.println("Invalid amount");
            return;
        }

        double amount = Double.parseDouble(amountField.getText());

        if (amount > totalBudgetAmount) {
            System.out.println("Amount exceeds budget");
            return;
        }
        amountField.setText(null);

        Budget newBudget = new Budget(name, amount, 0);
        budgetList.add(newBudget);
    }

    private void removeHandler() {
        Budget item = budgetTable.getSelectionModel().getSelectedItem();
        budgetList.remove(item);
    }

    private void quitHandler() {
        // save info?
        Platform.exit();
    }

    private void updateHandler() {
        // TODO totalBudget == 0, button says "Set", changes to "Update" after
        // TODO grey out and disable panel below separator until budget is set
        System.out.println("Updated total");
        totalBudgetAmount = Double.parseDouble(totalField.getText());
    }

    private void setHandlers() {
        totalButton.setOnAction(e -> updateHandler());
        addButton.setOnAction(e -> addHandler());
        removeButton.setOnAction(e -> removeHandler());
        viewButton.setOnAction(e -> overviewHandler());
        quitButton.setOnAction(e -> quitHandler());
    }

//    FOR DEBUGGING - make sure class extends Application
    public void start(Stage stage) {
        final int WIDTH = 750;
        final int HEIGHT = 450;
        Scene scene = createScene();
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

}
