package com.budget.budgettracking;

import java.text.NumberFormat;
import java.util.Map;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Class for creating BreakdownTab: a custom tab to display a PieChart
 */
public class BreakdownTabBudget extends Tab {

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();


    /**
     * Create BreakdownTab and PieChart
     * @param map: Map containing categories as keys and total costs for each category as values
     */
    public BreakdownTabBudget(Map<String, Double> map) {

        setText("Spending Breakdown");

        BorderPane bp = new BorderPane();
        Button quit = new Button("Quit");
        VBox vBox = new VBox();
        HBox quitBox = new HBox();

        PieChart chart = createPieChart(map);

        // Circle hole = new Circle(100);
        // hole.setFill(Color.WHITE);
        // hole.setStroke(Color.BLACK);
        // hole.setStrokeWidth(2);
        // hole.setTranslateY(-25.0);
        // hole.setScaleX(.5);
        // hole.setScaleY(.5);

        // Text totalText = new Text();
        // double totalValue = pieChartData.stream().mapToDouble(PieChart.Data::getPieValue).sum();
        // totalText.setTranslateY(-25.0);
        // totalText.setStyle("-fx-font-size: 14px;");
        // totalText.setText(String.format("%.1f%%", totalValue));
        // totalText.setFill(Color.BLACK);

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
     * Method creates PieChart from map data
     * @param map: Map containing categories as keys and total costs for each category as values
     * @return PieChart chart
     */
    public static PieChart createPieChart(Map<String, Double> map) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        );
        Double count = 0.0;

        for (Map.Entry<String, Double> item : map.entrySet()) {
            String category = item.getKey();
            Double amount = item.getValue();
            count += amount;

            pieChartData.add(new PieChart.Data(category, amount));
        }
        final Double total = count;

        PieChart chart = new PieChart(pieChartData);

        chart.setStyle("-fx-font-family: Roboto-Regular; -fx-font-size: 14px;");
        chart.setTitle("Spending Breakdown");
        Label titleLabel = (Label) chart.lookup(".chart-title");
        titleLabel.setStyle("-fx-font-family: Roboto-Regular; -fx-font-size: 20px;");

        chart.setLabelLineLength(20);
        // chart.getData().forEach(data -> {
        //     String label = String.format("%s: $%.2f %.2f%%", data.getName(), data.getPieValue(), ((data.getPieValue()/total) * 100));
        //     data.setName(label);
        // });

        chart.getData().forEach(data -> {
            String label = String.format("%s: %.2f%%", data.getName(), ((data.getPieValue()/total) * 100));
            data.setName(label);
        });

        return chart;
    }
}
