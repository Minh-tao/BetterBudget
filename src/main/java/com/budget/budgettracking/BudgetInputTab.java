package com.budget.budgettracking;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;

public class BudgetInputTab extends Tab{
    // visual components
    Tab inputTab = new Tab();
    ScrollPane scrollPane = new ScrollPane();
    VBox vBox = new VBox();
    HBox totalBar = new HBox();
    Label totalLabel = new Label("Total Budget"); // tooltip -> amount allowed to spend/month
    TextField totalField = new TextField();
    Button totalButton = new Button("Set Total");
    HBox totalDisplayBox = new HBox();
    Label totalDisplayLabel = new Label("");

    Separator lineSeparator = new Separator();

    HBox categoryBar = new HBox();
    VBox categoryLabelBox = new VBox();
    Label categoryLabel = new Label("Categories");
    VBox nameBox = new VBox();
    Label nameLabel = new Label("Name");
    VBox amountBox = new VBox();
    Label amountLabel = new Label("Amount");
    TextField amountField = new TextField();
    VBox buttonBox = new VBox();
    Label blank = new Label("");
    Button addButton = new Button("Add");

    VBox tableBox = new VBox();
    HBox tableLabelBox = new HBox();
    Label tableLabel = new Label("Current Categories"); // does this need its own box?

    HBox bottomBar = new HBox();
    Button quitButton = new Button("Quit");

    // initial budget, TODO should be loaded from user data
    double totalBudgetAmount = 0.0;

    // dropdown for budget category name
    ObservableList<String> nameList = FXCollections.observableArrayList(Arrays.asList( // list of categories
            "Clothing", "Debt payments", "Education", "Entertainment", "Food", "Gifts and donations",
            "Health and wellness", "Housing", "Insurance", "Personal care",
            "Savings", "Taxes", "Transportation", "Miscellaneous"
    ));
    ComboBox<String> nameCombo = new ComboBox<>(nameList);

    // table of budget categories
    ObservableList<Budget> budgetList = FXCollections.observableArrayList();
    TableView<Budget> table = new TableView<>(budgetList);

    /**
     *
     * @return scene budget input window
     */
    public BudgetInputTab() {

        allStyling();
        populate();
        setHandlers();
        tableSetup();
        test();
//        createToolTips();
        scrollPane.setContent(vBox);
        setText("Budget Input");
        setContent(scrollPane);
    }

    private void allStyling() {
        inputTab.setClosable(false);

        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        totalBar.setSpacing(10);
        totalBar.setAlignment(Pos.CENTER);
        totalBar.setPadding(new Insets(5));
//        totalLabel.setPrefWidth(100);
//        totalLabel.getStyleClass().add("label");
        totalField.setMinWidth(200);
        totalField.setPrefWidth(200);
        totalField.setPromptText("Enter amount in dollars (e.g. 1000)");
        totalField.setAlignment(Pos.CENTER);
        totalButton.setPrefWidth(75);

        lineSeparator.setPrefWidth(800);

        categoryBar.setSpacing(10);
        categoryBar.setAlignment(Pos.CENTER);
        categoryBar.setPadding(new Insets(10));
        categoryLabelBox.setPadding(new Insets(10, 0, 0, 0));
        categoryLabelBox.setSpacing(10);
        categoryLabelBox.setAlignment(Pos.CENTER);
//        nameLabel.setPrefWidth(100);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setPrefWidth(160);
        nameBox.setMaxWidth(Double.MAX_VALUE);
        nameCombo.setPrefWidth(160);
        nameCombo.setPromptText("Select or add a name");
        amountLabel.setPrefWidth(100);
        amountBox.setAlignment(Pos.CENTER);
        amountBox.setPrefWidth(150);
        amountBox.setMaxWidth(Double.MAX_VALUE);
        amountField.setPrefWidth(100);
        amountField.setPromptText("Enter amount in dollars"); // or just "$"
//        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10, 10, 10, 10));
        buttonBox.setAlignment(Pos.CENTER_LEFT);
//        addButton.setPrefWidth(80);

        tableBox.setAlignment(Pos.CENTER);
//        tableBox.setSpacing(10);
//        tableBox.setPadding(new Insets(10));
        tableLabelBox.setAlignment(Pos.CENTER);

        bottomBar.setPadding(new Insets(10, 0, 0, 0));
        bottomBar.setAlignment(Pos.CENTER_RIGHT);
        quitButton.setPrefWidth(80);

        totalLabel.getStyleClass().add("custom-label");
        totalDisplayLabel.getStyleClass().add("custom-label");
        categoryLabel.getStyleClass().add("custom-label");
        nameLabel.getStyleClass().add("custom-label");
        amountLabel.getStyleClass().add("custom-label");
        tableLabel.getStyleClass().add("custom-label");

        totalBar.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: black;"
                + "-fx-background-color: #7AE1B5");
        categoryBar.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: black;"
                + "-fx-background-color: #7AE1B5");
        tableBox.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: black;"
                + "-fx-background-color: LIGHTGREY;");

    }

    private void populate() {
        vBox.getChildren().addAll(totalBar, categoryBar, tableBox, bottomBar);
        totalBar.getChildren().addAll(totalLabel, totalField, totalButton);
        categoryBar.getChildren().addAll(categoryLabelBox, nameBox, amountBox, buttonBox);
        tableBox.getChildren().addAll(tableLabelBox, table);
        bottomBar.getChildren().add(quitButton);

        // categoryBar Boxes
        categoryLabelBox.getChildren().add(categoryLabel);
        nameBox.getChildren().addAll(nameLabel, nameCombo);
        amountBox.getChildren().addAll(amountLabel, amountField);
        buttonBox.getChildren().addAll(blank, addButton);
    }

    private void tableSetup() {
        TableColumn<Budget, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Budget, String> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amountColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Budget, String> percentColumn = new TableColumn<>("% of Total Budget");
        percentColumn.setCellValueFactory(cellData -> {
            Budget budget = cellData.getValue();
            double percentage = (budget.getAmount() / totalBudgetAmount) * 100;
            return new SimpleStringProperty(String.format("%.2f%%", percentage));
        });
        percentColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn delCol = new TableColumn();
//        delCol.setStyle("-fx-alignment: CENTER;");
        Image delete = new Image("delete.png");
        // TODO set delete column cell factory
        delCol.setCellFactory(ButtonTableCell.<Budget>forTableColumn(delete, (Budget Budget) ->
        {
            removeHandler(Budget);
            return Budget;
        }));

        table.setSelectionModel(null);
        table.setEditable(false);
        table.setItems(budgetList);
        table.getColumns().addAll(nameColumn, amountColumn, percentColumn, delCol); // removed timeColumn
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void removeHandler(Budget toRemove) {
        table.getItems().remove(toRemove);
//        refreshViewTab();
    }

//    private void refreshViewTab() {
//        chartTab = createChartTab();
//        tabPane.getTabs().set(1, chartTab);
//    }

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
        Budget item = table.getSelectionModel().getSelectedItem();
        budgetList.remove(item);
    }

    private void quitHandler() {
        // save info?
        Platform.exit();
    }

    private void setTotalHandler() {
        // TODO totalBudget == 0, button says "Set", changes to "Update" after
        // TODO grey out and disable panel below separator until budget is set
        System.out.println("Updated total");
        totalBudgetAmount = Double.parseDouble(totalField.getText());
    }

    private void setHandlers() {
        totalButton.setOnAction(e -> setTotalHandler());
        addButton.setOnAction(e -> addHandler());
//        removeButton.setOnAction(e -> removeHandler());
//        viewButton.setOnAction(e -> overviewHandler());
        quitButton.setOnAction(e -> quitHandler());
    }

    private void createToolTips(){
        // tooltips
        nameCombo.setTooltip(new Tooltip("Select or add a new category name"));

        quitButton.setTooltip(new Tooltip("Save and quit the application"));
    }

    private void test() {
        totalBudgetAmount = 2000;
        budgetList.add(new Budget("Food", 250));
        budgetList.add(new Budget("Health", 100));
        budgetList.add(new Budget("Rent", 1000));
        budgetList.add(new Budget("Transportation", 120));
        budgetList.add(new Budget("Personal", 200));
        budgetList.add(new Budget("Misc", totalBudgetAmount - 1670)); // make misc the remaining unallocated amount

//        createView();
    }

}
