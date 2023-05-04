package com.budget.budgettracking;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class BudgetView extends Tab {

    TabPane tabPane;
    VBox vBox = new VBox();
    HBox hBox = new HBox();
    Button editButton = new Button("Edit Budget");
    Button quitButton = new Button("Quit");

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    public BudgetView(TabPane tabPane, double totalBudget, ObservableList<Budget> list) {
        this.tabPane = tabPane;
        setText("Budget Overview");

        BorderPane bp = new BorderPane();
        quitButton.setOnAction(e -> {Platform.exit();});
        quitButton.setPrefWidth(75);
        editButton.setOnAction(e -> {tabPane.getSelectionModel().select(0);});
        hBox.getChildren().addAll(editButton, quitButton);
        hBox.setAlignment(Pos.TOP_RIGHT);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));

        double sum = sumCategories(list);
        pieChartData.add(new PieChart.Data("Extra", totalBudget - sum));
        addToPieChartData(list);
        PieChart chart = new PieChart(pieChartData);
        chart.setStyle("-fx-font-family: Roboto-Regular; -fx-font-size: 14px;");
        chart.setTitle("Spending Breakdown");
        Label titleLabel = (Label) chart.lookup(".chart-title");
        titleLabel.setStyle("-fx-font-family: Roboto-Regular; -fx-font-size: 20px;");
        chart.setLabelLineLength(20);

        // show percentages in chart and legend
        chart.getData().forEach(data -> {
            String label = String.format("%s: %.2f%%", data.getName(), ((data.getPieValue()/totalBudget) * 100));
            data.setName(label);
        });

        bp.setCenter(chart);
        bp.setBottom(hBox);
        bp.setStyle("-fx-background-color: linear-gradient(to top, #F2FFDB, #00BB62);");
        setContent(bp);
    }
    // tab for BudgetInput tabpane
    // user can view visual representation of budget piechart and metrics
    // returns tab

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

}
