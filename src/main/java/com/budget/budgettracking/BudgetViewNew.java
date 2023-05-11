package com.budget.budgettracking;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BudgetViewNew extends Tab {

    ScrollPane scrollPane = new ScrollPane();
    VBox vBox = new VBox();
    HBox hBox = new HBox();
    Button editButton = new Button("Edit Budget");
    Button quitButton = new Button("Quit");

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    ObservableList<Transaction> mockData = FXCollections.observableArrayList(); // DEBUG

    private double totalBudgetAmount;
    private BudgetInputTab budgetInputTab;
    //    private BudgetList budgetList;
//    private TransactionList transactionList;
    private ObservableList<Budget> budgetList;
    private ObservableList<Transaction> transactionList;
    private PieChart pieChart;
    private StackedBarChart<String, Number> stackedBarChart;

    public BudgetViewNew(double totalBudgetAmount, ObservableList<Budget> budgetList, ObservableList<Transaction> transactionList) {
        setText("Budget Overview");

        this.totalBudgetAmount = totalBudgetAmount;
        this.budgetList = budgetList;
        this.transactionList = transactionList;

//        BorderPane bp = new BorderPane();
        quitButton.setOnAction(e -> {Platform.exit();});
        quitButton.setPrefWidth(75);
//        editButton.setOnAction(e -> {tabPane.getSelectionModel().select(0);});
        hBox.getChildren().addAll(editButton, quitButton);
        hBox.setAlignment(Pos.TOP_RIGHT);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));

        double sum = sumBudgets(budgetList);
        pieChartData.add(new PieChart.Data("Extra", totalBudgetAmount - sum));
        addToPieChartData(budgetList);
        PieChart chart = new PieChart(pieChartData);
        chart.setStyle("-fx-font-family: Roboto-Regular; -fx-font-size: 14px;");
        chart.setTitle("Budget Allocation");
        Label titleLabel = (Label) chart.lookup(".chart-title");
        titleLabel.setStyle("-fx-font-family: Roboto-Regular; -fx-font-size: 20px;");
        chart.setLabelLineLength(20);

        // create pie chart
        pieChart = new PieChart();
        pieChart.setTitle("Budget Allocation");
        pieChart.setLegendSide(Side.RIGHT);
        updatePieChart();

        // create stacked bar chart
        createSBC(budgetList, transactionList);

        // Add a listener to update the charts whenever the lists change
        budgetList.addListener((ListChangeListener<Budget>) e -> updateCharts());
        transactionList.addListener((ListChangeListener<Transaction>) e -> updateCharts());

        // show percentages in chart and legend
        chart.getData().forEach(data -> {
            String label = String.format("%s: %.2f%%", data.getName(), ((data.getPieValue()/totalBudgetAmount) * 100));
            data.setName(label);
        });

        addMockData();
//        StackedBarChart stackedBarChart = createSBC(list, mockData);

        vBox.getChildren().addAll(chart, stackedBarChart);
        vBox.setFillWidth(true);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        scrollPane.setContent(vBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        setContent(scrollPane);
    }

    public Tab getTab() {
        return this; // create tab instance var and return instead?
    }

    private void updateCharts() {
        updatePieChart();
        updateSBC();
    }

    private PieChart createPieChart() {return null;}
    private ObservableList<PieChart.Data> createPieChartData() {return null;}
    private StackedBarChart<String, Number> createStackedChart() {return null;}
    private StackedBarChart createSBC(ObservableList<Budget> bList, ObservableList<Transaction> tList) {
        // set x axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("Food", "Health", "Rent", "Transportation", "Personal", "Misc")));
        xAxis.setLabel("Category");

        // set y axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("$");

        StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);
        stackedBarChart.setTitle("Budget Usage by Category");

        XYChart.Series<String, Number> spentSeries = new XYChart.Series<>();
        spentSeries.setName("Spent");
        for (Transaction item : tList) {
            spentSeries.getData().add(new XYChart.Data<>(item.getCategory(), item.getAmount()));
        }

        XYChart.Series<String, Number> totalSeries = new XYChart.Series<>();
        totalSeries.setName("Total");
        for (Budget item : bList) {
            totalSeries.getData().add(new XYChart.Data<>(item.getName(), item.getAmount()));
        }

        stackedBarChart.getData().addAll(spentSeries, totalSeries);

        // animation
        final Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(spentSeries.getData().get(0).YValueProperty(), 0),
                        new KeyValue(totalSeries.getData().get(0).YValueProperty(), 0)
                )
        );
        timeline.play();

        return stackedBarChart;
    }
    private ObservableList<XYChart.Series<String, Number>> createSBCData() {return null;}
    private void updatePieChart() {
        // get totalBudgetAmount
        double sum = sumBudgets(budgetList);
        double extraAmount = totalBudgetAmount - sum;

        // create pie chart data

        // set data and colors

        // set labels
    }
    private void updateSBC() {
        stackedBarChart.setPrefSize(800, 600);
        // create series data
        XYChart.Series<String, Number> spentSeries = new XYChart.Series<>();
        spentSeries.setName("Spent");
        XYChart.Series<String, Number> totalSeries = new XYChart.Series<>();
        totalSeries.setName("Total");

        for (Budget budget : budgetList) {
            String budgetName = budget.getName();
            double budgetAmount = budget.getAmount();
            double spentAmount = budget.getCurrent();


        }
    }

    private double sumBudgets(ObservableList<Budget> list) {
        double sum = 0;
        for (Budget item : list) {
            sum += item.getAmount();
        }
        return sum;
    }

    private void addToPieChartData(ObservableList<Budget> list) {
        for (Budget item : list) {
            pieChartData.add(new PieChart.Data(item.getName(), item.getAmount()));
        }
    }

    private void addMockData() {
        // map for chart
        mockData.add(new Transaction("Doctor", 75, "Health", LocalDate.now()));
        mockData.add(new Transaction("Burger", 60, "Food", LocalDate.now()));
        mockData.add(new Transaction("Rent", 1000, "Rent", LocalDate.now().minusMonths(1)));
        mockData.add(new Transaction("Gas", 70, "Transportation", LocalDate.now().minusMonths(2)));
        mockData.add(new Transaction("Metro Pass", 27, "Transportation", LocalDate.now().minusMonths(2)));
        mockData.add(new Transaction("Minecraft", 25, "Personal", LocalDate.now().minusMonths(3)));
        mockData.add(new Transaction("Dog Food", 20, "Misc", LocalDate.now().minusMonths(2)));
    }

}
