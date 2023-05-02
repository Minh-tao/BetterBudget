package com.budget.budgettracking;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Objects;

public class BudgetInput {
    // visual components
        TabPane tp = new TabPane();
        Tab inputTab = new Tab();
        GridPane outerGridpane = new GridPane();
        GridPane panelGrid = new GridPane();
        VBox inputPanel = new VBox();
        FlowPane flow = new FlowPane();
        VBox nameBox = new VBox();
        VBox amountBox = new VBox();
        VBox timeBox = new VBox();
        VBox tableBox = new VBox();
        VBox buttonBox = new VBox();
        HBox addBox = new HBox();
        HBox totalBudgetBox = new HBox();

        // widgets
        Label totalLabel = new Label("Total Budget: ");
        Label addHeaderLabel = new Label("Add Budget Category");
        Label tableHeaderLabel = new Label("Current Categories");
        Label nameLabel = new Label("Name: ");
        Label amountLabel = new Label("Amount: ");
        Label timeLabel = new Label("Time Period: "); // frequency, occurency, repeating:

        TextField totalField = new TextField("$ ");
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

        Button addButton = new Button("Add Budget Category");
        Button viewButton = new Button("View Budget Overview");
        Button quitButton = new Button("Quit");

        // dropdown for budget category name
        ObservableList<String> nameList = FXCollections.observableArrayList( // list of categories
                "Clothing", "Debt payments", "Education", "Entertainment", "Food", "Gifts and donations",
                "Health and wellness", "Housing", "Insurance", "Personal care",
                "Savings and investments", "Taxes", "Transportation", "Miscellaneous"
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
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm());
        return scene;
    }

    private void allStyling() {
        // box alignment
        nameBox.setAlignment(Pos.CENTER);
        amountBox.setAlignment(Pos.TOP_CENTER);
        timeBox.setAlignment(Pos.TOP_CENTER);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        totalBudgetBox.setAlignment(Pos.TOP_LEFT);
        tableBox.setAlignment(Pos.TOP_CENTER);
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(170); // preferred width allows for two columns
        flow.setStyle("-fx-background-color: DAE6F3;");

        // label styling (sizes, font)
        Font titlefont = Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf"), 18);
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf"), 14);

        addHeaderLabel.getStyleClass().add("title");
        tableHeaderLabel.getStyleClass().add("title");
        nameLabel.getStyleClass().add("custom-label");
        totalLabel.getStyleClass().add("custom-label");
        amountLabel.getStyleClass().add("custom-label");
        timeLabel.getStyleClass().add("custom-label");

        // widget styling
        nameField.setMaxWidth(125);
        nameField.setPrefWidth(125);
        nameCombo.setMaxWidth(125);
        nameCombo.setPrefWidth(125);
        nameCombo.setEditable(true);
        amountField.setMaxWidth(75);
        amountField.setPromptText("$");
        addButton.setPrefWidth(200);
        totalField.setPromptText("$");
        totalField.setMaxWidth(75);
        totalField.setAlignment(Pos.CENTER_RIGHT);
        quitButton.setPrefWidth(75);
        addButton.setFont(font);
        amountField.setFont(font);
        quitButton.setFont(font);
        totalField.setFont(font);
        otherField.setPromptText("Enter frequency");
        otherField.setDisable(true);


        // box/grid styling
        outerGridpane.setAlignment(Pos.CENTER);
        outerGridpane.setStyle("-fx-background-color: linear-gradient(to bottom, #2FC9ED, #F6CE55);");
        outerGridpane.setPadding(new Insets(10, 10, 10, 10));
        outerGridpane.setHgap(10);
        panelGrid.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: black;"
                + "-fx-padding: 0;"
                + "-fx-background-color: LIGHTCYAN");
        tableBox.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: black;"
                + "-fx-background-color: LIGHTGREY;");
        buttonBox.setStyle("-fx-background-color: transparent;");
    }

    private void gridConstraints() {
//        outerGridpane.setGridLinesVisible(true);
//        panelGrid.setGridLinesVisible(true);
        // outerGridPane styling
        ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(40);
        ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(60);
//        RowConstraints row1 = new RowConstraints();
//            row1.setMaxHeight(25);
//            row1.setPrefHeight(0);
//            row1.setVgrow(Priority.SOMETIMES);
//        RowConstraints row2 = new RowConstraints();
//            row2.setMaxHeight(261);
//            row2.setMinHeight(10);
//            row2.setPrefHeight(261);
//            row2.setVgrow(Priority.SOMETIMES);
//        RowConstraints row3 = new RowConstraints();
//            row3.setMinHeight(10);
//            row3.setPrefHeight(30);
//            row3.setVgrow(Priority.SOMETIMES);
//        RowConstraints row4 = new RowConstraints();
//            row4.setMinHeight(10);
//            row4.setPrefHeight(10);
//            row4.setVgrow(Priority.SOMETIMES);
        outerGridpane.getColumnConstraints().addAll(column1, column2);
//        outerGridpane.getRowConstraints().addAll(row1, row2, row3, row4);
//
        inputPanel.setPadding(new Insets(10, 10, 10, 10));
//
//        ColumnConstraints innerColumn1 = new ColumnConstraints();
//        innerColumn1.setHgrow(Priority.SOMETIMES);
//        innerColumn1.setMinWidth(10);
//        innerColumn1.setPrefWidth(50);
//        ColumnConstraints innerColumn2 = new ColumnConstraints();
//        innerColumn2.setHgrow(Priority.SOMETIMES);
//        innerColumn2.setMinWidth(10);
//        innerColumn2.setPrefWidth(50);
//
//        RowConstraints innerRow1 = new RowConstraints();
//        innerRow1.setMaxHeight(66.5);
//        innerRow1.setMinHeight(10);
//        innerRow1.setPrefHeight(40.5);
//        innerRow1.setVgrow(Priority.SOMETIMES);
//        RowConstraints innerRow2 = new RowConstraints();
//        innerRow2.setMaxHeight(120.5);
//        innerRow2.setMinHeight(10);
//        innerRow2.setPrefHeight(57.0);
//        innerRow2.setVgrow(Priority.SOMETIMES);
//
//        panelGrid.getColumnConstraints().addAll(innerColumn1, innerColumn2);
//        panelGrid.getRowConstraints().addAll(innerRow1, innerRow2);
    }

    private void populate() {
        // add to grid
        totalBudgetBox.getChildren().addAll(totalLabel, totalField);
        nameBox.getChildren().addAll(nameLabel, nameCombo);
        amountBox.getChildren().addAll(amountLabel, amountField);
        timeBox.getChildren().addAll(timeLabel, dailyRadioButton,
                weeklyRadioButton, monthlyRadioButton,
                annuallyRadioButton, otherRadioButton, otherField);
        addBox.getChildren().addAll(addButton);
        tableBox.getChildren().addAll(budgetTable);
        buttonBox.getChildren().addAll(viewButton, quitButton);
        flow.getChildren().addAll(timeLabel, dailyRadioButton,
                weeklyRadioButton, monthlyRadioButton,
                annuallyRadioButton, otherRadioButton, otherField);

        outerGridpane.add(totalBudgetBox, 0, 0);
        outerGridpane.add(addHeaderLabel, 0, 1);
        outerGridpane.add(inputPanel, 0, 2);
            inputPanel.getChildren().addAll(nameBox, amountBox, flow);
        outerGridpane.add(addBox, 0, 3);
        outerGridpane.add(tableHeaderLabel, 1, 1);
        outerGridpane.add(tableBox, 1, 2);
        outerGridpane.add(buttonBox, 1, 3);

    }

    private void tableSetup() {
        TableColumn<Budget, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Budget, String> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Budget, String> percentColumn = new TableColumn<>("% of Total Budget");
        percentColumn.setCellValueFactory(new PropertyValueFactory<>("percent"));

        TableColumn<Budget, String> timeColumn = new TableColumn<>("Time Period");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        budgetTable.setEditable(false);
        budgetTable.setItems(budgetList);
        budgetTable.getColumns().addAll(nameColumn, amountColumn, percentColumn, timeColumn);
        budgetTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }


    private void otherHandler() {// Enable the TextField when the "Other" radio button is selected
        otherRadioButton.setOnAction(event -> {
            otherField.setDisable(!otherRadioButton.isSelected());
            System.out.println("other pressed");
        });
    }

    private void overviewHandler() {
        tp.getSelectionModel().select(1); //index, or tab name, or selectNext()
    }

    private void addHandler() {
        // limit must be <= total budget
        // reset name dropdown
        // reset limit field
        // un-toggle radio buttons
        // reset other field
    }

    private void quitHandler() {
        // save info?
        Platform.exit();
    }

    private void setHandlers() {
//        addButton.setOnAction(e -> addHandler());
        viewButton.setOnAction(e -> overviewHandler());
        quitButton.setOnAction(e -> quitHandler());
    }

}
