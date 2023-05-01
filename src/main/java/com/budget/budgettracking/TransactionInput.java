package com.budget.budgettracking;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

/** Class used to create SpendingApp GUI and add functionality
 *
 */
public class TransactionInput extends Application{
    private static final int WIDTH = 750;
    private static final int HEIGHT = 450;

    // visual components
    TabPane tabPane = new TabPane();
    Tab shopTab = new Tab();
    TransactionView chartTab;
    GridPane outerGrid = new GridPane();
    GridPane inputPanel = new GridPane();
    VBox nameBox = new VBox();
    VBox priceBox = new VBox();
    VBox categoryBox = new VBox();
    VBox qtyBox = new VBox();
    HBox qtyControls = new HBox();
    HBox purchaseBox = new HBox();
    GridPane bottomGrid = new GridPane();
    HBox totalBox = new HBox();
    HBox removeBox = new HBox();
    HBox quitBox = new HBox();
    HBox addTitleBox = new HBox();
    HBox pTitleBox = new HBox();
    VBox tableBox = new VBox();

    // labels
    Label addItemLabel = new Label("Add Item");
    Label purchasedLabel = new Label("Purchased Items");
    Label nameLabel = new Label("Name");
    Label categoryLabel = new Label("Category");
    Label priceLabel = new Label("Price");
    Label qtyLabel = new Label("Quantity");
    Label totalLabel = new Label("Total ");

    // widgets
    TextField nameField = new TextField();
    TextField priceField = new TextField();
    TextField qtyField = new TextField();
    Button qMinus = new Button("-");
    Button qPlus = new Button("+");
    ComboBox<String> categoryComboBox = new ComboBox<>();
    Button purchaseButton = new Button("Purchase");
    Button removeButton = new Button("Remove Selected Item");
    TextField totalField = new TextField();
    Button quitButton = new Button("Quit");

    // lists
    ObservableList<Purchase> purchaseList = FXCollections.observableArrayList(
            new Purchase("Rent", 1, 1800.0, "Housing"),
            new Purchase("Food", 1, 300.0, "Food"),
            new Purchase("Subway Pass", 10, 3.50, "Transportation"),
            new Purchase("Vacation Fund", 1, 1000.0, "Savings and Investments")
    ); // hold purchases
    ObservableList<String> categoryList = FXCollections.observableArrayList( // list of categories
            "Clothing", "Debt payments", "Education", "Entertainment", "Food", "Gifts and donations",
            "Health and wellness", "Housing", "Insurance", "Personal care",
            "Savings and investments", "Taxes", "Transportation", "Miscellaneous"
    );

    // tableview
    TableView<Purchase> table = new TableView<>(purchaseList);

    /** Application start method
     *  @param stage: GUI stage
     */
    @Override
    public void start(Stage stage) {

        categoryComboBox = new ComboBox<>(categoryList); // fill category dropdown with categories

        // methods
        gridStyling();
        allStyling();
        setButtonHandlers();
        createToolTips();
        populateGrids();
        tableSetup();
        bindTotalField();

        // create and add tabs to tabPane
        shopTab.setText("Purchases");
        shopTab.setContent(outerGrid);
        chartTab = createChartTab();
        chartTab.setText("Spending Breakdown");
        tabPane.getTabs().addAll(shopTab, chartTab);
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); // prevent user from closing tabs

        // set the scene
        VBox root = new VBox(tabPane);
        Scene scene = new Scene(root, Color.LIGHTBLUE);
        scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm());
        stage.setResizable(false);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Spending Visualizer");
        stage.show();
    }

    /** Helper function binds purchaseList to the totalField to display the total cost of all items
     *
     */
    private void bindTotalField() {
        // Add a listener to the purchaseList
        purchaseList.addListener((ListChangeListener<Purchase>) change -> {
            // Recalculate the total cost
            double totalCost = 0;
            for (Purchase purchase : purchaseList) {
                totalCost += purchase.getPrice() * purchase.getQty();
            }
            // Update the total field with the new total cost
            String str = String.format("$ %.2f", totalCost);
            totalField.setText(str);
        });

        // Bind the total field to the total cost
        totalField.textProperty().bindBidirectional(new SimpleStringProperty("0"));
    }

    /** Helper function creates TableColumn objects and adds them to TableView object
     *
     */
    private void tableSetup() {
        // create table columns
        TableColumn<Purchase, String> itemCol = new TableColumn<>("Item");
        itemCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Purchase, Integer> qtyCol = new TableColumn<>("Qty");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));

        TableColumn<Purchase, Double> priceCol = new TableColumn<>("Cost/unit");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Purchase, String> catCol = new TableColumn<>("Category");
        catCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        table.setEditable(false);
        table.setItems(purchaseList);
        table.getColumns().addAll(itemCol, qtyCol, priceCol, catCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /** Helper function sets handlers for buttons
     *
     */
    private void setButtonHandlers() {
        // set button handlers
        qPlus.setOnAction(e -> qtyHandler("inc"));
        qMinus.setOnAction(e -> qtyHandler("dec"));
        removeButton.setOnAction(e -> removeHandler());
        purchaseButton.setOnAction(e -> purchaseHandler());
        quitButton.setOnAction(e -> quitHandler());
    }

    /** Helper function adds widgets to boxes and adds those boxes to the GridPanes in the scene
     *
     */
    private void populateGrids() {
        // add to grid
        addTitleBox.getChildren().add(addItemLabel);
        outerGrid.add(addTitleBox, 0, 0);
        outerGrid.add(inputPanel, 0, 1);
        inputPanel.add(nameBox, 0, 0);
        nameBox.getChildren().addAll(nameLabel, nameField);

        inputPanel.add(priceBox, 1, 0);
        priceBox.getChildren().addAll(priceLabel, priceField);

        inputPanel.add(categoryBox, 0, 1);
        categoryBox.getChildren().addAll(categoryLabel, categoryComboBox);

        inputPanel.add(qtyBox, 1, 1);
        qtyBox.getChildren().addAll(qtyLabel, qtyControls);
        qtyControls.getChildren().addAll(qMinus, qtyField, qPlus);

        inputPanel.add(purchaseBox, 0, 2, 2, 1);
        purchaseBox.getChildren().add(purchaseButton);

        pTitleBox.getChildren().add(purchasedLabel);
        outerGrid.add(pTitleBox, 1, 0);
        tableBox.getChildren().add(table);
        outerGrid.add(tableBox, 1, 1);
        outerGrid.add(bottomGrid, 1, 2);
        bottomGrid.add(removeBox, 0, 0);
        removeBox.getChildren().add(removeButton);
        bottomGrid.add(totalBox, 1, 0);
        totalBox.getChildren().addAll(totalLabel, totalField);
        bottomGrid.add(quitBox, 1, 1);
        quitBox.getChildren().add(quitButton);
    }

    /** Helper function sets box, label, and widget styling
     *
     */
    private void allStyling() {
        // box alignment
        nameBox.setAlignment(Pos.CENTER);
        priceBox.setAlignment(Pos.TOP_CENTER);
        categoryBox.setAlignment(Pos.TOP_CENTER);
        qtyBox.setAlignment(Pos.TOP_CENTER);
        qtyControls.setAlignment(Pos.TOP_CENTER);
        purchaseBox.setAlignment(Pos.BOTTOM_CENTER);
        totalBox.setAlignment(Pos.TOP_RIGHT);
        removeBox.setAlignment(Pos.TOP_LEFT);
        quitBox.setAlignment(Pos.BOTTOM_RIGHT);

        // label styling (sizes, font)
        Font titlefont = Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf"), 18);
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf"), 14);
        addItemLabel.setFont(titlefont);
        addTitleBox.setAlignment(Pos.BOTTOM_CENTER);
        purchasedLabel.setFont(titlefont);
        pTitleBox.setAlignment(Pos.BOTTOM_CENTER);
        nameLabel.setFont(font);
        priceLabel.setFont(font);
        categoryLabel.setFont(font);
        qtyLabel.setFont(font);
        totalLabel.setFont(font);
        totalLabel.setAlignment(Pos.CENTER_RIGHT);

        // widget styling
        nameField.setMaxWidth(125);
        nameField.setPrefWidth(125);
        priceField.setMaxWidth(75);
        priceField.setPromptText("$");
        qtyField.setMaxWidth(25);
        qtyField.setText("1");
        qtyField.setAlignment(Pos.CENTER);
        purchaseButton.setPrefWidth(100);
        totalField.setPromptText("$");
        totalField.setEditable(false);
        totalField.setMaxWidth(75);
        totalField.setAlignment(Pos.CENTER_RIGHT);
        quitButton.setPrefWidth(75);
        purchaseButton.setFont(font);
        removeButton.setFont(font);
        qMinus.setFont(font);
        qMinus.setStyle("-fx-font-weight: bold;");
        qMinus.setPrefHeight(30);
        qPlus.setFont(font);
        qPlus.setStyle("-fx-font-weight: bold;");
        qPlus.setPrefHeight(30);
        priceField.setFont(font);
        quitButton.setFont(font);
        qtyField.setFont(font);
        totalField.setFont(font);

        // box/grid styling
        outerGrid.setAlignment(Pos.CENTER);
        outerGrid.setStyle("-fx-background-color: linear-gradient(to bottom, #2FC9ED, #F6CE55);");
        outerGrid.setPadding(new Insets(10, 10, 10, 10));
        outerGrid.setHgap(10);
        // addTitleBox.setStyle("-fx-background-color: transparent;");
        // pTitleBox.setStyle("-fx-background-color: transparent;");
        inputPanel.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: black;"
                + "-fx-padding: 30;"
                + "-fx-background-color: LIGHTCYAN");
        tableBox.setStyle("-fx-border-style: solid inside;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: black;"
                + "-fx-background-color: LIGHTGREY;");
        bottomGrid.setStyle("-fx-background-color: transparent;");
    }

    /** Helper function creates ColumnConstraints and RowConstraints for GridPanes and sets their height/width
     *
     */
    private void gridStyling() {
        // gridpane styling
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(40);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(60);

        RowConstraints row1 = new RowConstraints();
        row1.setMaxHeight(25);
        row1.setPrefHeight(0);
        row1.setVgrow(Priority.SOMETIMES);

        RowConstraints row2 = new RowConstraints();
        row2.setMaxHeight(261);
        row2.setMinHeight(10);
        row2.setPrefHeight(261);
        row2.setVgrow(Priority.SOMETIMES);

        RowConstraints row3 = new RowConstraints();
        row3.setMinHeight(10);
        row3.setPrefHeight(30);
        row3.setVgrow(Priority.SOMETIMES);

        outerGrid.getColumnConstraints().addAll(column1, column2);
        outerGrid.getRowConstraints().addAll(row1, row2, row3);

        inputPanel.setVgap(50);
        inputPanel.setHgap(10);

        ColumnConstraints innerColumn1 = new ColumnConstraints();
        innerColumn1.setHgrow(Priority.SOMETIMES);
        innerColumn1.setMinWidth(10);
        innerColumn1.setPrefWidth(50);

        ColumnConstraints innerColumn2 = new ColumnConstraints();
        innerColumn2.setHgrow(Priority.SOMETIMES);
        innerColumn2.setMinWidth(10);
        innerColumn2.setPrefWidth(50);

        RowConstraints innerRow1 = new RowConstraints();
        innerRow1.setMaxHeight(66.5);
        innerRow1.setMinHeight(10);
        innerRow1.setPrefHeight(40.5);
        innerRow1.setVgrow(Priority.SOMETIMES);

        RowConstraints innerRow2 = new RowConstraints();
        innerRow2.setMaxHeight(120.5);
        innerRow2.setMinHeight(10);
        innerRow2.setPrefHeight(57.0);
        innerRow2.setVgrow(Priority.SOMETIMES);

        RowConstraints innerRow3 = new RowConstraints();
        innerRow3.setMaxHeight(115.5);
        innerRow3.setMinHeight(10);
        innerRow3.setPrefHeight(103.5);
        innerRow3.setVgrow(Priority.SOMETIMES);

        inputPanel.getColumnConstraints().addAll(innerColumn1, innerColumn2);
        inputPanel.getRowConstraints().addAll(innerRow1, innerRow2, innerRow3);

        bottomGrid.setPadding(new Insets(10, 0, 0, 0));
        bottomGrid.setHgap(10);
        bottomGrid.setVgap(10);
        bottomGrid.setAlignment(Pos.TOP_CENTER);

        ColumnConstraints bottomColumn1 = new ColumnConstraints();
        bottomColumn1.setPrefWidth(235);
        ColumnConstraints bottomColumn2 = new ColumnConstraints();
        bottomColumn2.setPrefWidth(235);

        RowConstraints bottomRow1 = new RowConstraints();
        bottomRow1.setPrefHeight(100);
        RowConstraints bottomRow2 = new RowConstraints();
        bottomRow2.setPrefHeight(100);

        bottomGrid.getColumnConstraints().addAll(bottomColumn1, bottomColumn2);
        bottomGrid.getRowConstraints().addAll(bottomRow1, bottomRow2);
    }

    /** Helper function sets tooltips for widgets
     *
     */
    private void createToolTips(){
        // tooltips
        nameField.setTooltip(new Tooltip("Enter item name"));
        priceField.setTooltip(new Tooltip("Enter item price"));
        categoryComboBox.setTooltip(new Tooltip("Select a category relevant to the item"));
        qtyField.setTooltip(new Tooltip("Enter item quantity"));
        qMinus.setTooltip(new Tooltip("Decrease quantity by 1"));
        qPlus.setTooltip(new Tooltip("Increase quantity by 1"));
        purchaseButton.setTooltip(new Tooltip("Add item to the table"));
        table.setTooltip(new Tooltip("Purchased items will appear here"));
        removeButton.setTooltip(new Tooltip("Click on a row and press this button to remove it from the table"));
        totalField.setTooltip(new Tooltip("The total cost of all purchased items"));
        shopTab.setTooltip(new Tooltip("Enter item purchases to be analyzed"));
        quitButton.setTooltip(new Tooltip("Close the application"));
    }

    /**
     * Method gets the sums for each item category, and calls TransactionView class to create a tab for a chart object
     * @return TransactionView: tab containing the chart
     */
    private TransactionView createChartTab() {
        // map for pie chart
        MapProperty<String, Double> map = new SimpleMapProperty<>(FXCollections.observableHashMap());
        Double prevTotal;
        double categoryTotal;
        for (Purchase item : purchaseList) {
            String key = item.getCategory();
            Integer amt = item.getQty();
            Double value = item.getPrice();

            if (map.containsKey(key)) {         // add cost of current row to previous in same category
                prevTotal = map.get(key);
                categoryTotal = (amt * value) + prevTotal;
            } else {                            // if row's category hasn't been put in map yet
                categoryTotal = (amt * value);  // multiplying cost by qty
            }
            map.put(key, categoryTotal);
        }
        TransactionView TransactionView = new TransactionView(map);
        TransactionView.setTooltip(new Tooltip("Shows a breakdown of the given purchased items"));
        return TransactionView;
    }

    /**
     * Creates new Purchase object based on user input values and adds Purchase to list
     */
    private void purchaseHandler() {
        int quantity = Integer.parseInt(qtyField.getText());
        double cost = Double.parseDouble(priceField.getText());
        String category = (String)categoryComboBox.getValue();
        String name = nameField.getText();
        Purchase newBuy = new Purchase(name, quantity, cost, category);
        qtyField.setText("1");
        priceField.setText(null);
        purchaseList.add(newBuy);
        refreshPieTab();
    }

    /**
     * Removes selected row from the TableView object
     */
    private void removeHandler() {
        Purchase selectedItem = table.getSelectionModel().getSelectedItem();
        purchaseList.remove(selectedItem);
        refreshPieTab();
    }

    /**
     * Quits out of the application
     */
    private void quitHandler() {
        Platform.exit();
    }

    /**
     * Creates new PieChart tab and replaces the previous one
     */
    private void refreshPieTab() {
        chartTab = createChartTab();
        tabPane.getTabs().set(1, chartTab);
    }

    /**
     * Increment or decrement quantity count depending on which button is pressed
     * @param mode: whether the quantity button pressed is "-" or "+"
     */
    private void qtyHandler(String mode) {
        int num = Integer.parseInt(qtyField.getText());
        if (Objects.equals(mode, "inc")) {
            qtyField.setText(Integer.toString(num + 1));
        } else {
            if (num > 0) {
                qtyField.setText(Integer.toString(num - 1));
            }
        }
    }

    /**
     * Class for Purchase objects that are to be stored in list/table
     */
    public static class Purchase {
        private final SimpleStringProperty name;
        private final SimpleIntegerProperty qty;
        private final SimpleDoubleProperty price;
        private final SimpleStringProperty category;

        private Purchase(String name, int qty, double uPrice, String category) {
            this.name = new SimpleStringProperty(name);
            this.qty = new SimpleIntegerProperty(qty);
            this.price = new SimpleDoubleProperty(uPrice);
            this.category = new SimpleStringProperty(category);
        }

        public String getName() {
            return name.get();
        }

        public int getQty() {
            return qty.get();
        }

        public double getPrice() {
            return price.get();
        }

        public String getCategory() {
            return category.get();
        }

        public void setName(String n) {
            name.set(n);
        }

        public void setQty(int q) {
            qty.set(q);
        }

        public void setPrice(Double p) {
            price.set(p);
        }

        public void setCategory(String c) {
            category.set(c);
        }
    }

    public static void main(String[] args) { launch(args); }
}