package com.budget.budgettracking;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;

import java.util.*;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.time.format.TextStyle;
import java.time.Month;


/**
 * Class for creating BreakdownTab: a custom tab to display a LineChart
 */
public class TransactionView extends Tab {

    /**
     * Create BreakdownTab and LineChart
     * @param list: Map containing categories as keys and total costs for each category as values
     */
    public TransactionView(List<Transaction> list) {

        setText("Spending Breakdown");

        BorderPane bp = new BorderPane();
        Button quit = new Button("Quit");
        VBox vBox = new VBox();
        HBox quitBox = new HBox();

        LineChart<String, Number> chart = createChart(list);

        Font font = Font.loadFont("file:resources/fonts/Roboto/Roboto-Regular.ttf", 14);

        quit.setOnAction(e -> quitHandler());
        quit.setPrefWidth(75);
        quit.setMaxWidth(75);
        quit.setFont(font);
        quitBox.getChildren().addAll(quit);
        quitBox.setAlignment(Pos.BOTTOM_RIGHT);
        quitBox.setPadding(new Insets(10, 10, 9, 0));

        vBox.getChildren().addAll(chart, quitBox);
        vBox.setAlignment(Pos.BOTTOM_RIGHT);
        vBox.setPadding(new Insets(0, 0, 0, 0));
        vBox.setStyle("-fx-background-color: linear-gradient(to bottom, #2FC9ED, #F6CE55);");
        this.setTooltip(new Tooltip("Shows a breakdown of the given purchased items"));

        // sp.getChildren().addAll(chart, hole, totalText);
        bp.setCenter(chart);
        setContent(bp);
    }

    /**
     * Closes the application
     */
    private void quitHandler() {
        Platform.exit();
    }

    /**
     * Method creates LineChart from map data
     * @param list: Map containing categories as keys and total costs for each category as values
     * @return LineChart chart
     */
    public static LineChart<String, Number> createChart(List<Transaction> list) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        yAxis.setLabel("Amount Spent");
        final LineChart<String,Number> chart =
                new LineChart<>(xAxis,yAxis);

        chart.setTitle("Stock Monitoring, 2010");
        chart.setStyle("-fx-font-family: Roboto-Regular; -fx-font-size: 14px;");
        Label titleLabel = (Label) chart.lookup(".chart-title");
        titleLabel.setStyle("-fx-font-family: Roboto-Regular; -fx-font-size: 20px;");

        createChartData(list, chart);

        return chart;
    }

    private static void createChartData(List<Transaction> transactions, LineChart<String,Number> chart)
    {
        Map<String, Map<String, Double>> chartData = new HashMap<>();

        // Each category will represent one line/entry in the map
        for (Transaction t : transactions)
        {
            String c = t.getCategory();
            String tMonth = t.getDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.getDefault());
            Double tAmount = t.getAmount();
            if (!chartData.containsKey(c))
            {
                Map<String, Double> months = addMonthMap();
                months.put(tMonth, months.get(tMonth) + tAmount);
                chartData.put(c, months);
            } else
            {
                Map<String, Double> months = chartData.get(c);
                months.put(tMonth, months.get(tMonth) + tAmount);
            }
        }

        for (Map.Entry<String, Map<String, Double>> category : chartData.entrySet()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(category.getKey());

            for (int i = 1; i <= 12; i++) {
                String month = Month.of(i).getDisplayName(TextStyle.SHORT, Locale.getDefault());
                series.getData().add(new XYChart.Data(month, category.getValue().get(month)));
            }
            chart.getData().add(series);
        }

    }

    // Each category stores the total amount spent in each month in a map
    // key month, value: total spent on this category this month
    private static Map<String, Double> addMonthMap()
    {
        Map<String, Double> months = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            String month = Month.of(i).getDisplayName(TextStyle.SHORT, Locale.getDefault());
            months.put(month, 0.0);
        }
        return months;
    }
}