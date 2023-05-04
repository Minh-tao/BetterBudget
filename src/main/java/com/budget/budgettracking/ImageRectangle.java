package com.budget.budgettracking;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ImageRectangle extends Pane {
    private Rectangle TopBanner;
    private ImageView logo;

    public ImageRectangle(double width, double height) {
        TopBanner = new Rectangle(width, height);
        TopBanner.setFill(Color.rgb(244, 244, 244));

        logo = new ImageView(new Image("Logo.png"));
        logo.setPreserveRatio(true);
        logo.setFitHeight(height * 0.8);
        logo.setFitWidth(height * 0.8);
        logo.layoutXProperty().bind(TopBanner.widthProperty().subtract(logo.fitWidthProperty()).divide(2.25));
        logo.layoutYProperty().bind(TopBanner.heightProperty().subtract(logo.fitHeightProperty()).divide(2));


        getChildren().addAll(TopBanner, logo);
    }

    public void setHeight(double height) {
        TopBanner.setHeight(height);
        logo.setFitHeight(height * 0.8);
        logo.setFitWidth(height * 0.8);
        logo.setY((height - logo.getFitHeight()) / 2);
    }

    public void bindWidth(javafx.beans.property.ReadOnlyDoubleProperty widthProperty) {
        TopBanner.widthProperty().bind(widthProperty);
    }


}
