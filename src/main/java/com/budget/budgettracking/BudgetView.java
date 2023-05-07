package com.budget.budgettracking;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class BudgetView extends Tab {

    ScrollPane scrollPane = new ScrollPane();
    VBox vBox = new VBox();
    HBox hBox = new HBox();
    Button editButton = new Button("Edit Budget");
    Button quitButton = new Button("Quit");

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    ObservableList<Transaction> mockData = FXCollections.observableArrayList(); // DEBUG

    public BudgetView(double totalBudget, ObservableList<Budget> list) {

        setText("Budget Overview");

//        BorderPane bp = new BorderPane();
        quitButton.setOnAction(e -> {Platform.exit();});
        quitButton.setPrefWidth(75);
//        editButton.setOnAction(e -> {tabPane.getSelectionModel().select(0);});
        hBox.getChildren().addAll(editButton, quitButton);
        hBox.setAlignment(Pos.TOP_RIGHT);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));

        double sum = sumCategories(list);
        pieChartData.add(new PieChart.Data("Extra", totalBudget - sum));
        addToPieChartData(list);
        PieChart chart = new PieChart(pieChartData);
        chart.setStyle("-fx-font-family: Roboto-Regular; -fx-font-size: 14px;");
        chart.setTitle("Budget Allocation");
        Label titleLabel = (Label) chart.lookup(".chart-title");
        titleLabel.setStyle("-fx-font-family: Roboto-Regular; -fx-font-size: 20px;");
        chart.setLabelLineLength(20);

        // show percentages in chart and legend
        chart.getData().forEach(data -> {
            String label = String.format("%s: %.2f%%", data.getName(), ((data.getPieValue()/totalBudget) * 100));
            data.setName(label);
        });

        addMockData();
        StackedBarChart stackedBarChart = createSBC(list, mockData);

        vBox.getChildren().addAll(chart, stackedBarChart);

        scrollPane.setContent(vBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        setContent(scrollPane);
    }

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

//        Timeline tl = new Timeline();
//        tl.getKeyFrames().add(
//                new KeyFrame(Duration.millis(500),
//                        new EventHandler<ActionEvent>() {
//                            @Override public void handle(ActionEvent actionEvent) {
//                                for (XYChart.Series<String, Number> series : stackedBarChart.getData()) {
//                                    for (XYChart.Data<String, Number> data : series.getData()) {
//                                        data.setYValue(Math.random() * 1000);
//                                    }
//                                }
//                            }
//                        }
//                ));
//        tl.setCycleCount(Animation.INDEFINITE);
//        tl.setAutoReverse(true);
//        tl.play();
//
//        xAxis.setAnimated(false);

        //TODO set the color of each bar in totalSeries to match its pie slice
        // set color of each bar in spentSeries to a single color
        // create a startup animation
        // pie startup animation too?

        return stackedBarChart;
    }


    private double sumCategories(ObservableList<Budget> list) {
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
