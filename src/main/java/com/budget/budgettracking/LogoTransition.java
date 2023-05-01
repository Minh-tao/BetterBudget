package com.budget.budgettracking;

import javafx.animation.Transition;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LogoTransition extends Transition {

    private final ImageView logo;
    private final Text welcomeText;
    private final Separator separator;
    private final Pane loginPane;

    public LogoTransition(ImageView logo, Text welcomeText, Separator separator, Pane loginPane) {
        this.logo = logo;
        this.welcomeText = welcomeText;
        this.separator = separator;
        this.loginPane = loginPane;
        setCycleDuration(Duration.millis(2900));
    }

    @Override
    protected void interpolate(double frac) {
        double translateY = -60 * frac;
        double fade = Math.min(1, 2 * frac);

        logo.setTranslateY(translateY);
        welcomeText.setTranslateY(translateY);
        separator.setTranslateY(translateY);
        loginPane.setOpacity(fade);
    }
}