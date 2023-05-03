package com.budget.budgettracking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.List;

public class BudgetView extends Tab {

    HBox hBox = new HBox();
    Button editButton = new Button("Edit Budget");
    Button quitButton = new Button("Quit");

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    public BudgetView(double totalBudget, ObservableList<Budget> list) {
        setText("Budget Overview");

        BorderPane bp = new BorderPane();
        hBox.getChildren().addAll(editButton, quitButton);

        double sum = sumCategories(list);
        pieChartData.add(new PieChart.Data("Extra", totalBudget - sum));
        addToPieChartData(list);
        PieChart chart = new PieChart(pieChartData);
        chart.setStyle("-fx-font-family: Roboto-Regular; -fx-font-size: 14px;");
        chart.setTitle("Spending Breakdown");
        Label titleLabel = (Label) chart.lookup(".chart-title");
        titleLabel.setStyle("-fx-font-family: Roboto-Regular; -fx-font-size: 20px;");
        chart.setLabelLineLength(20);
//        chart.getData().forEach(data -> {
//            String label = String.format("%s: %.2f%%", data.getName(), ((data.getPieValue()/total) * 100));
//            data.setName(label);
//        });
        bp.setCenter(chart);
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
